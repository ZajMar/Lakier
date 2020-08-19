package pl.lacquer.lacquerapp.model.comparator;

import org.springframework.stereotype.Component;

import pl.lacquer.lacquerapp.model.LacquerInternal;

import static pl.lacquer.lacquerapp.model.LacquerInternal.LACQUER_BRAND;

/**
 * Created by Marcin Zając on 2020-08-18
 */
@Component
public class LacquerInternalComparatorByBrand implements LacquerInternalComparator {

    @Override
    public boolean isForMe(String sortBy) {
        return LACQUER_BRAND.equals(sortBy);
    }

    @Override
    public int compare(LacquerInternal firstLacquer, LacquerInternal secondLacquer) {
        return firstLacquer.getLacquerBrand().compareToIgnoreCase(secondLacquer.getLacquerBrand());
    }
}
