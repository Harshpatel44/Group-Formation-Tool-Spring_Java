package CSCI5308.GroupFormationTool.UserManager;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static CSCI5308.GroupFormationTool.ApplicationConstants.guest;

@Repository
public class UserRepository implements IUserRepository {

    private static final Logger LOG = LogManager.getLogger();
    private DatabaseAbstractFactory databaseAbstractFactory;

    @Override
    public boolean createUser(IUser user) {
        Boolean success = false;
        StoredProcedure storedProcedure = null;
        try {
            databaseAbstractFactory = DatabaseAbstractFactory.instance();
            storedProcedure = databaseAbstractFactory.createStoredProcedure("spCreateUser(?, ?, ?, ?, ?,?)");
            storedProcedure.setParameter(1, user.getBannerId());
            storedProcedure.setParameter(2, user.getPassword());
            storedProcedure.setParameter(3, user.getFirstName());
            storedProcedure.setParameter(4, user.getLastName());
            storedProcedure.setParameter(5, user.getEmailId());
            storedProcedure.setParameter(6, user.getContactNumber());
            storedProcedure.execute();
            success = true;
            LOG.info("Operation = Create user" + user.getBannerId() + ", Status = success");

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Operation = Create user" + user.getBannerId() + ", Status = Fail, Error Message=" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = Create user" + user.getBannerId() + ", Status = Fail, Error Message=" + e.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return success;
    }


    @Override
    public boolean checkIfUserExists(String bannerID) {
        StoredProcedure storedProcedure = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedure("userByBannerID(?)");
            storedProcedure.setParameter(1, bannerID);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                if (results.next()) {
                    LOG.info("Operation = user exists with bannerId" + bannerID + ", Status = success");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = user exists" + bannerID + ", Status = Fail, Error Message=" + e.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        LOG.info("Operation = user not exists with bannerId" + bannerID + ", Status = success");
        return false;
    }

    @Override
    public IUser setUserByBannerId(String bannerID, IUser iUser) {
        StoredProcedure storedProcedure = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedure("userByBannerID(?)");
            storedProcedure.setParameter(1, bannerID);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                if (results.next()) {
                    iUser.setBannerId(results.getString("userId"));
                    iUser.setFirstName(results.getString("firstName"));
                    iUser.setLastName(results.getString("lastName"));
                    iUser.setPassword(results.getString("passwd"));
                    iUser.setEmailId(results.getString("email"));
                    iUser.setContactNumber(results.getString("contactNo"));
                    LOG.info("Operation = user set with bannerId:" + iUser.getBannerId() + ", Status = success");
                    return iUser;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = user not set with bannerId:" + iUser.getBannerId() + ", Status = fail, Error Message=" + e.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return iUser;
    }

    @Override
    public List<String> getAllBannerIds() {
        List<String> bannerIds = new ArrayList<String>();
        StoredProcedure storedProcedure = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedure("spGetAllUserIDs");
            ResultSet results = storedProcedure.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    bannerIds.add(results.getString(1));
                }
                LOG.info("Operation = fetch all bannerid , Status = success");
            }
            return bannerIds;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation =fetch all bannerid, Status = fail, Error Message=" + e.getMessage());
            return bannerIds;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
    }

    @Override
    public String checkUserRoleForCourse(String bannerID, String courseID) {
        StoredProcedure storedProcedure = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedure("CheckGuest(?,?)");
            storedProcedure.setParameter(1, bannerID);
            storedProcedure.setParameter(2, courseID);
            ResultSet rs = storedProcedure.executeWithResults();
            if (rs.next())//if data present, then not guest
            {
                LOG.info("Operation = check role , Status = success");
                return rs.getString("roleId");
            } else {
                LOG.info("Operation = check role , Status = success");
                return guest;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = check role, Status = fail, Error Message=" + e.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return null;
    }

    public boolean checkIfUserIsGuest(String bannerID) {
        boolean result = true;  //Guest
        StoredProcedure role = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try {
            role = databaseAbstractFactory.createStoredProcedure("CheckGuest(?)");
            role.setParameter(1, bannerID);
            ResultSet rs = role.executeWithResults();
            if (rs.next())//if data present, then not guest
            {
                result = false;//Not Guest
                LOG.info("Operation = check guest , Status = success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = check guest, Status = fail, Error Message=" + e.getMessage());
        } finally {
            if (role != null) {
                role.cleanup();
            }
        }
        return result;
    }

    public int getUserRoleIdFromRoleType(String userType) {
        StoredProcedure storedProcedure = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedure("getRoleIdFromRoleType(?)");
            storedProcedure.setParameter("rType", userType.toLowerCase());
            ResultSet rs = storedProcedure.executeWithResults();
            while (rs.next()) {
                LOG.info("Operation = get user role ,Role=" + userType + " , Status = success");
                return rs.getInt("roleId");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = get user role ,Role=" + userType + ", Status = fail, Error Message=" + e.getMessage());
            return 0;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return 0;
    }
}
