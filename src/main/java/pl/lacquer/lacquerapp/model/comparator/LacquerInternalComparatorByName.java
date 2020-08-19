package pl.lacquer.lacquerapp.model.comparator;

import org.springframework.stereotype.Component;

import pl.lacquer.lacquerapp.model.LacquerInternal;

import static pl.lacquer.lacquerapp.model.LacquerInternal.LACQUER_NAME;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Component
public class LacquerInternalComparatorByName implements LacquerInternalComparator {

    @Override
    public boolean isForMe(String sortBy) {
        return LACQUER_NAME.equals(sortBy);
    }

    @Override
    public int compare(LacquerInternal firstLacquer, LacquerInternal secondLacquer) {
        return firstLacquer.getLacquerName().compareToIgnoreCase(secondLacquer.getLacquerName());
    }
}
