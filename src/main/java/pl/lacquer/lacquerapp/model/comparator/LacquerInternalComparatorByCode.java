package pl.lacquer.lacquerapp.model.comparator;

import org.springframework.stereotype.Component;

import pl.lacquer.lacquerapp.model.LacquerInternal;

import static pl.lacquer.lacquerapp.model.LacquerInternal.LACQUER_CODE;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Component
public class LacquerInternalComparatorByCode implements LacquerInternalComparator {

    @Override
    public boolean isForMe(String sortBy) {
        return LACQUER_CODE.equals(sortBy);
    }

    @Override
    public int compare(LacquerInternal firstLacquer, LacquerInternal secondLacquer) {
        return firstLacquer.getLacquerCode().compareToIgnoreCase(secondLacquer.getLacquerCode());
    }
}
