package helpers;

import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.WebSocketService;
import com.github.kklisura.cdt.services.config.ChromeDevToolsServiceConfiguration;
import com.github.kklisura.cdt.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdt.services.impl.ChromeDevToolsServiceImpl;
import com.github.kklisura.cdt.services.impl.WebSocketServiceImpl;
import com.github.kklisura.cdt.services.invocation.CommandInvocationHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v101.emulation.Emulation;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.decorators.Decorated;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.github.kklisura.cdt.services.utils.ProxyUtils.createProxy;
import static com.github.kklisura.cdt.services.utils.ProxyUtils.createProxyFromAbstract;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class Cdp {
    public static void setGeolocationOverride(double latitude, double longitude) {
        getDevToolsWithCreatedSession().send(Emulation.setGeolocationOverride(
                Optional.of(latitude), Optional.of(longitude), Optional.of(1)));
    }

    public static void setLocaleOverride(String locale) {
        getDevToolsWithCreatedSession().send(Emulation.setLocaleOverride(Optional.of(locale)));
    }

    public static void setUserAgentOverride(String acceptLanguage) {
        //"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.66 Safari/537.36"
        getDevToolsWithCreatedSession().send(Emulation.setUserAgentOverride(
                requireNonNull(executeJavaScript("return navigator.userAgent;")),
                Optional.of(acceptLanguage),Optional.empty(), Optional.empty()));
    }

    public static DevTools getDevToolsWithCreatedSession() {
        DevTools devtools = getCdpDriver().getDevTools();
        devtools.createSession();
        return devtools;
    }

    @Nonnull
    @CheckReturnValue
    private static HasDevTools getCdpDriver() {
        WebDriver webDriver = getWebDriver();
        return getCdpDriver(webDriver);
    }

    @Nonnull
    @CheckReturnValue
    private static HasDevTools getCdpDriver(WebDriver webDriver) {
        if (webDriver instanceof HasDevTools) {
            return ((HasDevTools) webDriver);
        } else if (webDriver instanceof Decorated) {
            return getCdpDriver(((Decorated<WebDriver>) webDriver).getOriginal());
        } else if (webDriver instanceof WrapsDriver) {
            return getCdpDriver(((WrapsDriver) webDriver).getWrappedDriver());
        } else {
            WebDriver augmentedDriver = new Augmenter().augment(getWebDriver());
            return ((HasDevTools) augmentedDriver);
        }
    }

    public static ChromeDevToolsService from(final String webSocketURI)
            throws URISyntaxException, WebSocketServiceException {
        return from(WebSocketServiceImpl.create(new URI(webSocketURI)));
    }

    /**
     * Creates ChromeDevToolsService instance based on existing websocket.
     *
     * @param webSocketService CDP WS instance.
     */
    static ChromeDevToolsService from(final WebSocketService webSocketService) {
        final CommandInvocationHandler invocationHandler = new CommandInvocationHandler();
        final Map<Method, Object> commandsCache = new ConcurrentHashMap<>();
        final ChromeDevToolsService devtools =
                createProxyFromAbstract(
                        ChromeDevToolsServiceImpl.class,
                        new Class[]{WebSocketService.class, ChromeDevToolsServiceConfiguration.class},
                        new Object[]{webSocketService, new ChromeDevToolsServiceConfiguration()},
                        (unused, method, args) ->
                                commandsCache.computeIfAbsent(
                                        method, key -> createProxy(method.getReturnType(), invocationHandler)));
        invocationHandler.setChromeDevToolsService(devtools);
        return devtools;
    }

    public static void setAgentOverrideDependingOnTool(String tool) throws WebSocketServiceException, URISyntaxException {
        //TODO: make more common
        switch (tool) {
            case "ui_selenoid":
                ChromeDevToolsService devtools = Cdp.from(format("ws://selenoid.autotests.cloud:4444/devtools/%s/page", ((RemoteWebDriver) getWebDriver()).getSessionId().toString()));
                devtools.getEmulation().setUserAgentOverride(requireNonNull(executeJavaScript("return navigator.userAgent;")),
                        "en-US,en", null, null);
                break;
            case "ui_local":
                Cdp.setUserAgentOverride("en-US,en");
                break;
        }
    }
}
