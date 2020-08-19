package pl.lacquer.lacquerapp.model.comparator;

import org.springframework.stereotype.Component;

import pl.lacquer.lacquerapp.model.LacquerInternal;

import static pl.lacquer.lacquerapp.model.LacquerInternal.LACQUER_POPULARITY;

/**
 * Created by Marcin Zając on 2020-08-18
 */
@Component
public class LacquerInternalComparatorByPopularity implements LacquerInternalComparator {

    @Override
    public boolean isForMe(String sortBy) {
        return LACQUER_POPULARITY.equals(sortBy);
    }

    @Override
    public int compare(LacquerInternal firstLacquer, LacquerInternal secondLacquer) {
        return firstLacquer.getLacquerPopularity().compareToIgnoreCase(secondLacquer.getLacquerPopularity());
    }
}
