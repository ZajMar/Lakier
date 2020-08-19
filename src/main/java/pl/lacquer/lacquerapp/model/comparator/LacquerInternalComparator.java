package pl.lacquer.lacquerapp.model.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import pl.lacquer.lacquerapp.model.LacquerInternal;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Component
public interface LacquerInternalComparator extends Comparator<LacquerInternal> {

    boolean isForMe(String sortBy);
}
