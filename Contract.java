package ferry.contract;

import ferry.dto.FerryDetail;
import ferry.dto.RouteDetail;
import ferry.dto.ScheduleDetail;
import ferry.dto.TravelingEntityDetail;
import ferry.eto.DataAccessException;
import ferry.eto.NoSuchFerryException;
import ferry.eto.NoSuchHarbourException;
import ferry.eto.NoSuchRouteException;
import ferry.eto.NoSuchScheduleException;
import ferry.eto.OverBookedException;
import ferry.eto.NoSuchTravelingEntityException;
import java.util.Collection;
import java.util.Date;
import ferry.dto.FerryIdentifier;
import ferry.dto.SchedulesIdentifier;
import ferry.dto.RouteIdentifier;
import ferry.dto.TravelingEntityIdentifier;
import ferry.dto.DepartureDetail;

/**
 *
 * @author Kasper
 */
public interface AdminContract {

    /**
     * Changes the departure and arrival time for a ferry.
     *
     * @pre the ferry must not be null and exist and the departure.
     * @throws NoSuchFerryException if the ferry does not exist.
     * @throws DataAccessException if the data can not be stored.
     * @param ferryId
     * @param date
     * @param depatureID
     * @param delayTime
     * @post the departure and arrival time have been changed for the ferry
     */
    public void delayFerry(FerryIdentifier ferryId, Date date,int depatureID, int delayTime) throws NoSuchFerryException, DataAccessException;

    /**
     * Adds a new ferry to the system.
     *
     * @pre ferry don't already exist in the system.
     * @throws DataAccessException if the data can not be stored.
     * @param ferry
     * @post the new ferry is saved in the system.
     */
    public void addFerry(FerryDetail ferry) throws DataAccessException;

    /**
     * Deletes a ferry from the system
     *
     * @pre ferry must exist in the system
     * @throws NoSuchFerryException if the ferry is not found
     * @param ferry
     * @post ferry removed from the system
     */
    public void deleteFerry(FerryDetail ferry) throws NoSuchFerryException; // måske skal det bare være summary eller et id????

    /**
     * Cancel a ferry.
     *
     * @pre ferry must exist in the system
     * @throws NoSuchFerryException if the ferry is not found.
     * @throws DataAccessException if data cant accessed.
     * @param ferryId
     * @param date
     * @post the ferry have been canceled.
     */
    public void cancelFerry(FerryIdentifier ferryId, Date date) throws NoSuchFerryException, DataAccessException; //ændre til depature id??

    /**
     * Show a list of all ferries.
     *
     * @pre there must exist at least one ferry in system and it must not be null
     * @throws DataAccessException if data cant accessed.
     * @return Collection<FerryDetail>
     * @post the collection of ferries have been returned.
     *
     */
    public Collection<FerryDetail> showFerries() throws DataAccessException;

    /**
     * Adds a new schedule in the system
     *
     * @pre the schedule must not already exist in the system
     * @throws DataAccessException if the data cant be stored.
     * @param schedule
     * @post schedule have been saved.
     */
    public void addSchedule(ScheduleDetail schedule) throws DataAccessException;

    /**
     * Find a ferry
     *
     * @pre there must be a ferry in the system with the used id.
     * @throws NoSuchFerryException if the ferry is not found.
     * @param ferryID
     * @return FerryDetail
     * @post returns a ferry.
     */
    public FerryDetail findFerry(FerryIdentifier ferryID) throws NoSuchFerryException;

    /**
     * Update the Schedule information or timetable for a route;
     *
     * @@pre the schedule and timetables must exist.
     * @throws NoSuchScheduleException if schedule does not exist.
     * @throws DataAccessException if the data can not be stored.
     * @param schedule
     * @return ScheduleDetail the updated schedule
     * @post schedule have been updated
     */
    public ScheduleDetail updateSchedule(ScheduleDetail schedule) throws NoSuchScheduleException, DataAccessException;

    /**
     * Deletes a schedule from the system
     *
     * @pre there must exist a schedule with the given id.
     * @throws NoSuchScheduleException if schedule is not found.
     * @param scheduleID
     * @post schedule have been deleted.
     */
    public void deleteSchedule(SchedulesIdentifier scheduleID) throws NoSuchScheduleException;

    /**
     * Returns a list of schedules
     *
     * @pre there must at least exist one schedule.
     * @throws NoSuchScheduleException thrown if the specified scheduleID does
     * not exist.
     * @return Collection<ScheduleDetail>
     * @post a collection of schedules have been returned.
     */
    public Collection<ScheduleDetail> showSchedules() throws NoSuchScheduleException;

    /**
     * Return all schedules that is active for a given date.
     *
     * @pre there must at least exist one schedule for the given date.
     * @throws NoSuchScheduleException thrown if no schedules are found
     * @param date
     * @return Collection<ScheduleDetail>
     * @post returns all schedules that is active for the given date.
     */
    public Collection<ScheduleDetail> showSchedulesForDate(Date date) throws NoSuchScheduleException;

    /**
     * Adds a new route to the system.
     *
     * @pre the assigned harbours must exist
     * @throws NoSuchHarbourException if destination or orgin harbour does not
     * exist.
     * @throws DataAccessException if the data can not be stored.
     * @param routeDetail
     * @post the route have been added.
     */
    public void addRoute(RouteDetail routeDetail) throws NoSuchHarbourException, DataAccessException;

    /**
     * Shows all the routes currently in the system
     *
     * @pre the assigned harbours must exist
     * @throws NoSuchHarbourException if destination or orgin harbour does not
     * exist.
     * @return Collection<RouteDetail>
     * @post the collection of RouteDetails has been returned
     */
    public Collection<RouteDetail> showRoutes() throws NoSuchHarbourException;

    /**
     * @pre RouteDetail must exist in the system
     * @throws NoSuchRouteException if the routeId does not exist
     * @param routeId - id of the route to delete
     * @post the route has been deleted
     */
    public void deleteRoute(RouteIdentifier routeId) throws NoSuchRouteException;

    /**
     * @pre RouteDetail must exist in the system
     * @throws NoSuchRouteException if the routeId does not exist
     * @throws DataAccessException if the data can not be stored.
     * @param routeId the id of the RouteDetail to change the price for
     * @param newPrice the new base price for the RouteDetail
     * @return RouteDetail containing the new price and route information
     * @post the RouteDetail has been updated with the new price
     */
    public RouteDetail updatePrice(RouteIdentifier routeId, float newPrice) throws NoSuchRouteException, DataAccessException;

    /**
     * @param travelingEntity the entity to add
     * @throws DataAccessException if the data can not be stored. 
     * @post travelingEntity added to the system
     */
    public void addTravelingEntity(TravelingEntityDetail travelingEntity) throws DataAccessException;
    
    /**
     * @pre the TravelingEntity must exist in the system
     * @throws NoSuchTravelingEntityException if the TravelingEntity does not exist
     * @param travelingEntityId
     * @post The TravelingEntity is deleted from the system
     */
    public void deleteTravelingEntity(TravelingEntityIdentifier travelingEntityId) throws NoSuchTravelingEntityException;
    
    /**
     * @pre the TravelingEntity must exist in the system
     * @param travelingEntityDetail the TravelingEntity to update
     * @throws DataAccessException if the data can not be stored.
     * @throws NoSuchTravelingEntityException if the TravelingEntity does not exist
     * @post The TravelingEntity is updated with the new data
     */
    public void updateTravelingEntity(TravelingEntityDetail travelingEntityDetail) throws NoSuchTravelingEntityException, DataAccessException;
    /**
     * @pre the Depature must exist in the system
     * @param DepatureDe
     * @throws OverBookedException 
     * @post The Depature is updated with the new data
     */
    public void updateDepature(DepartureDetail DepatureDe) throws OverBookedException;
}
