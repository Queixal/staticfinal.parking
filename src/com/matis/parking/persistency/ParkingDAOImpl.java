package com.matis.parking.persistency;

import com.matis.cache.Database;
import com.matis.parking.entities.Parking;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.matis.utils.DoubleUtils;
import com.matis.utils.IntegerUtils;
import com.matis.utils.StringUtils;

/**
 *
 * @author MiguelAngel
 */
public class ParkingDAOImpl implements ParkingDAO {

    public void persist(Parking parkingToPersist) {
        Database.parkingsPersisted.put(parkingToPersist.getId(), parkingToPersist);
    }

    @Override
    public List<Parking> find(Parking parking) {
        List<Parking> listToReturn = new ArrayList<Parking>();
        for (Long id : (Set<Long>) Database.parkingsPersisted.keySet()) {
            Parking currentParking = Database.parkingsPersisted.get(id);

            boolean isTheSameObject = true;

            if (!StringUtils.isNull(parking.getName()) && !StringUtils.isBlank(parking.getName())) {
                if (currentParking.getName() == null) {
                    isTheSameObject = false;
                } else {

                    isTheSameObject &= currentParking.getName().equals(parking.getName());
                }
            }

            if (!IntegerUtils.isNull(parking.getId())) {
                if (currentParking.getId() == null) {
                    isTheSameObject = false;
                } else {

                    isTheSameObject &= currentParking.getId().longValue() == parking.getId().longValue();
                }
            }

            if (!IntegerUtils.isNull(parking.getOpeningHour())) {
                if (currentParking.getOpeningHour() == null) {
                    isTheSameObject = false;
                } else {

                    isTheSameObject &= currentParking.getOpeningHour().intValue() == parking.getOpeningHour().intValue();
                }
            }

            if (!IntegerUtils.isNull(parking.getClosingHour())) {
                if (currentParking.getClosingHour() == null) {
                    isTheSameObject = false;
                } else {

                    isTheSameObject &= currentParking.getClosingHour().intValue() == parking.getClosingHour().intValue();
                }
            }

            if (!IntegerUtils.isNull(parking.getSize())) {
                if (currentParking.getSize() == null) {
                    isTheSameObject = false;
                } else {
                    isTheSameObject &= currentParking.getSize().intValue() == parking.getSize();
                }
            }

            if (parking.getLocation() != null) {
                if (currentParking.getLocation() == null) {
                    isTheSameObject = false;
                } else {

                    if (!DoubleUtils.isNull(parking.getLocation().getLatitude())) {
                        isTheSameObject &= currentParking.getLocation().getLatitude().doubleValue() == parking.getLocation().getLatitude().doubleValue();
                    }

                    if (!DoubleUtils.isNull(parking.getLocation().getLongitude())) {
                        isTheSameObject &= parking.getLocation().getLongitude().doubleValue() == parking.getLocation().getLongitude().doubleValue();
                    }
                }
            }

            if (parking.getOpeningDays() != null) {
                if (currentParking.getOpeningDays() == null) {
                    isTheSameObject = false;
                } else {
                    isTheSameObject &= parking.getOpeningDays().containsAll(currentParking.getOpeningDays());
                }
            }

            if (isTheSameObject) {
                listToReturn.add(currentParking);
            }
        }

        return listToReturn;
    }

    @Override
    public void delete(Parking parkingToDelete
    ) {
        Database.parkingsPersisted.remove(parkingToDelete.getId());
    }

    @Override
    public Long getSequence() {
        for (Long id : (Set<Long>) Database.parkingsPersisted.keySet()) {
            if (!Database.parkingsPersisted.containsKey(id + 1)) {
                return id + 1;
            }
        }
        return 0L;
    }
}
