package CSCI5308.GroupFormationTool.GroupFormmer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GroupFormmerRepo implements IGroupFormmerRepo {

	private static final Logger LOG = LogManager.getLogger();

	@Override
	public boolean saveGroupFormula(IGroupFilter groupFilter, String courseID) {
		StoredProcedure storedProcedure = null;
		if (this.deleteGroupFormula(courseID)) {
			try {
				for (Map.Entry<Integer, Boolean> question : groupFilter.getquestionSimilarOrDissimilar().entrySet()) {
					int similarity = 0;
					int hasLessThanX = 0;
					int hasGreaterThanX = 0;
					int lessthanXValue = 0;
					int greaterThanXValue = 0;
					if (question.getValue()) {
						similarity = 1;
					}
					if (groupFilter.getConsiderLessThanOrGreaterThanX().containsKey(question.getKey())) {
						hasLessThanX = 1;
						hasGreaterThanX = 1;
						lessthanXValue = groupFilter.getConsiderLessThanOrGreaterThanX().get(question.getKey()).get(0);
						greaterThanXValue = groupFilter.getConsiderLessThanOrGreaterThanX().get(question.getKey())
								.get(1);
					}

					storedProcedure = new StoredProcedure("spCreateGroupFormula(?,?,?,?,?,?,?)");
					storedProcedure.setParameter(1, courseID);
					storedProcedure.setParameter(2, question.getKey());
					storedProcedure.setParameter(3, similarity);
					storedProcedure.setParameter(4, hasLessThanX);
					storedProcedure.setParameter(5, hasGreaterThanX);
					storedProcedure.setParameter(6, lessthanXValue);
					storedProcedure.setParameter(7, greaterThanXValue);
					storedProcedure.execute();
					LOG.info("Operation = Save group formula for course "+courseID+", Status = Success");
				}

			} catch (Exception e) {
				LOG.error("Operation = Save group formula for course "+courseID+", Status = Fail, Error Message="+e.getMessage());
				e.printStackTrace();
			} finally {
				if (storedProcedure != null) {
					storedProcedure.cleanup();
				}
			}
		}
		return false;
	}

	@Override
	public boolean deleteGroupFormula(String courseID) {
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("spDeleteGroupFormula(?)");
			storedProcedure.setParameter(1, courseID);
			storedProcedure.execute();
			LOG.info("Operation = Delete group formula for course "+courseID+", Status = Success");
			return true;
		} catch (Exception e) {
			LOG.error("Operation = Delete group formula for course "+courseID+", Status = Fail, Error Message="+e.getMessage());
			e.printStackTrace();
		} finally {
			if (storedProcedure != null) {
				storedProcedure.cleanup();
			}
		}
		return false;
	}

	@Override
	public IGroupFilter getGroupFormula(String courseID) {
		IGroupFilter groupFilter = Injector.instance().getGroupFilter();
		HashMap<Integer, Boolean> similaritySetForquestion = new HashMap<Integer, Boolean>();
		HashMap<Integer, ArrayList<Integer>> considerLessThanOrGreaterThanX = new HashMap<Integer, ArrayList<Integer>>();
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("spGetGroupFormula(?)");
			storedProcedure.setParameter(1, courseID);
			ResultSet results = storedProcedure.executeWithResults();
			if (results != null) {
				while (results.next()) {
					if (results.getInt("similarity") == 1) {
						similaritySetForquestion.put(results.getInt("questionID"), true);
					} else {
						similaritySetForquestion.put(results.getInt("questionID"), false);
					}

					if (results.getInt("hasLessThanX") == 1 && results.getInt("hasGreaterThanX") == 1) {
						ArrayList<Integer> values = new ArrayList<Integer>();
						values.add(results.getInt("lessThanXValue"));
						values.add(results.getInt("greaterThanXValue"));
						considerLessThanOrGreaterThanX.put(results.getInt("questionID"), values);
					}
					groupFilter.setquestionSimilarOrDissimilar(similaritySetForquestion);
					groupFilter.setConsiderLessThanOrGreaterThanX(considerLessThanOrGreaterThanX);

				}
			}
			LOG.info("Operation = Get group formula for course "+courseID+", Status = Success");
		} catch (Exception e) {
			LOG.error("Operation = Get group formula for course "+courseID+", Status = Fail, Error Message="+e.getMessage());
			e.printStackTrace();
		} finally {
			if (storedProcedure != null) {
				storedProcedure.cleanup();
			}
		}
		return groupFilter;
	}

}
