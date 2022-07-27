package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Pagination {

    public static <T> List<T> getAllPages(Function<Integer, List<T>> requestOnePage) {
        List<T> result = new ArrayList<>();
        List<T> onePage;
        int page = 1;
        do {
            onePage = requestOnePage.apply(page);
            result.addAll(onePage);
            page++;
        } while (!onePage.isEmpty());
        return result;
    }
}
