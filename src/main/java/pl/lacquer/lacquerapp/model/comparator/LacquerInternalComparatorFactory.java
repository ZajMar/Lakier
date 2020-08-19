package pl.lacquer.lacquerapp.model.comparator;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lacquer.lacquerapp.model.LacquerInternal;

import static pl.lacquer.lacquerapp.common.CommonQueryUtils.ASC;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Service
public class LacquerInternalComparatorFactory {

    private List<LacquerInternalComparator> comparators;

    @Autowired
    public LacquerInternalComparatorFactory(List<LacquerInternalComparator> comparators) {
        this.comparators = comparators;
    }

    public Comparator<LacquerInternal> getComparator(String sortBy, String order) {
        for (LacquerInternalComparator comparator : comparators) {
            if (comparator.isForMe(sortBy)) {
                return getComparatorOrder(comparator, order);
            }
        }
        throw new IllegalStateException("Not found comparator for provided sortBy: " + sortBy + " and order: " + order);
    }

    private Comparator<LacquerInternal> getComparatorOrder(LacquerInternalComparator comparator, String order) {
        return ASC.equals(order) ? comparator : comparator.reversed();
    }
}
