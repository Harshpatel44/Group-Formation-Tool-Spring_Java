package CSCI5308.GroupFormationTool.GroupFormmer;

import java.util.*;

import java.util.stream.Collectors;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AnswerSurvey.ISurveyQuestionOptionsModel;
import org.apache.commons.text.similarity.LevenshteinDetailedDistance;
import org.apache.commons.text.similarity.LevenshteinResults;

import javax.servlet.http.HttpServletRequest;

public class GroupFormmerService implements IGroupFormmerService {

	@Override
	public HashMap<Integer,ArrayList<String>> FormGroups(String courseID, int teamSize) {

		List<ISurveyQuestionOptionsModel> questions = Injector.instance().getAnswerSurveyRepository()
				.getSurveyQuestionsAndOptions(courseID);

		List<String> userAnsweredSurveyBasedOnCourseId = Injector.instance().getDisplaySurveyResponseRepository()
				.getUsersWhoAnsweredSurvey(courseID);

		HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> studentWithQuestionAndAnswer = Injector
				.instance().getDisplaySurveyResponseRepository().getSurveyResponse_2(courseID);

		IGroupFilter groupFilter = Injector.instance().getGrFormmerRepo().getGroupFormula(courseID);

		HashMap<String, Integer> indexUserBannerIdToIndex = new HashMap<String, Integer>();
		HashMap<Integer, String> indexUserIndexToBannerID = new HashMap<Integer, String>();
		int index = 0;
		for (String userBannerId : userAnsweredSurveyBasedOnCourseId) {

			indexUserBannerIdToIndex.put(userBannerId, index);
			indexUserIndexToBannerID.put(index, userBannerId);
			index++;
		}

		HashMap<String, List<ISurveyQuestionOptionsModel>> questionsBasedOnType = new HashMap<String, List<ISurveyQuestionOptionsModel>>();

		for (ISurveyQuestionOptionsModel question : questions) {
			if (questionsBasedOnType.containsKey(question.getSurveyQuestionType())) {
				questionsBasedOnType.get(question.getSurveyQuestionType()).add(question);

			} else {
				List<ISurveyQuestionOptionsModel> eachTypeQuestions = new ArrayList<ISurveyQuestionOptionsModel>();
				eachTypeQuestions.add(question);
				questionsBasedOnType.put(question.getSurveyQuestionType(), eachTypeQuestions);
			}
		}

		HashMap<Integer, ArrayList<ArrayList<Double>>> finalMatrices = new HashMap<Integer, ArrayList<ArrayList<Double>>>();
		HashMap<String, HashMap<Integer, Integer>> additionalMappings = new HashMap<String, HashMap<Integer, Integer>>();
		for (Map.Entry<String, List<ISurveyQuestionOptionsModel>> entry : questionsBasedOnType.entrySet()) {

			if (entry.getKey().equals("txt")) {
				HashMap<Integer, ArrayList<ArrayList<Double>>> typeMatrices = formTextMatrixForAllStudents(
						entry.getValue(), userAnsweredSurveyBasedOnCourseId.size(), studentWithQuestionAndAnswer, true,
						groupFilter);
				finalMatrices.put(1, AddMatrices(typeMatrices, true, userAnsweredSurveyBasedOnCourseId.size()));
			} else if (entry.getKey().equals("numeric")) {
				HashMap<Integer, ArrayList<ArrayList<Double>>> typeMatrices = formSingleChoiceOrNumericMatrixForAllStudents(
						entry.getValue(), userAnsweredSurveyBasedOnCourseId.size(), studentWithQuestionAndAnswer, true,
						groupFilter);
				finalMatrices.put(2, AddMatrices(typeMatrices, true, userAnsweredSurveyBasedOnCourseId.size()));

				Integer lessThanX = 5;
				Integer greaterThanX = 10;
				additionalMappings = GetAdditonalNumericMappings(entry.getValue(), lessThanX, greaterThanX,
						studentWithQuestionAndAnswer, indexUserBannerIdToIndex, groupFilter);

			} else if (entry.getKey().equals("mcqs")) {
				HashMap<Integer, ArrayList<ArrayList<Double>>> typeMatrices = formSingleChoiceOrNumericMatrixForAllStudents(
						entry.getValue(), userAnsweredSurveyBasedOnCourseId.size(), studentWithQuestionAndAnswer, true,
						groupFilter);
				finalMatrices.put(3, AddMatrices(typeMatrices, true, userAnsweredSurveyBasedOnCourseId.size()));
			} else if (entry.getKey().equals("mcqm")) {
				HashMap<Integer, ArrayList<ArrayList<Double>>> typeMatrices = formMultiChoiceMatrixForAllStudents(
						entry.getValue(), userAnsweredSurveyBasedOnCourseId.size(), studentWithQuestionAndAnswer, true,
						groupFilter);
				finalMatrices.put(4, AddMatrices(typeMatrices, true, userAnsweredSurveyBasedOnCourseId.size()));
			}

		}

		ArrayList<ArrayList<Double>> finalTotalMatrices = AddMatrices(finalMatrices, false,
				userAnsweredSurveyBasedOnCourseId.size());

		for (int i = 0; i < finalTotalMatrices.size(); i++) {
			for (int j = 0; j < finalTotalMatrices.get(i).size(); j++) {
				System.out.print(finalTotalMatrices.get(i).get(j));
				System.out.print(" ");
			}
			System.out.println();
		}
		HashMap<Integer, ArrayList<String>> teamsWithBannerID = groupFormationAlgorithm(finalTotalMatrices, additionalMappings, teamSize,
				userAnsweredSurveyBasedOnCourseId.size(), indexUserIndexToBannerID);
		return teamsWithBannerID;
	}

	private HashMap<Integer, ArrayList<String>> groupFormationAlgorithm(ArrayList<ArrayList<Double>> finalTotalMatrices,
										 HashMap<String, HashMap<Integer, Integer>> additionalMappings, Integer teamSize, int students,
			HashMap<Integer, String> indexUserIndexToBannerID) {
		// TODO Auto-generated method stub
		HashMap<Integer, ArrayList<Integer>> teams = new HashMap();
		HashMap<Integer, ArrayList<String>> teamsWithBannerID = new HashMap();

		int count = 0;

		HashMap<Integer, Integer> studentLessThanX = new HashMap<>();
		HashMap<Integer, Integer> studentGreaterthanX = new HashMap<>();
		if (additionalMappings.containsKey("lessThanX")) {
			studentLessThanX = additionalMappings.get("lessThanX");
		}
		if (additionalMappings.containsKey("greaterThanX")) {
			studentGreaterthanX = additionalMappings.get("greaterThanX");
		}

		System.out.println("number less than  value");
		studentLessThanX.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " " + entry.getValue());
		});

		System.out.println("Team formation");
		// Team Formation
		ArrayList<Integer> selected_students = new ArrayList<>();
		for (int x = 0; x < finalTotalMatrices.size(); x++) {

			if (!selected_students.contains(x)) {
				ArrayList<Integer> selected_students_1 = this.generate_team(finalTotalMatrices.get(x), teamSize,
						studentLessThanX, studentGreaterthanX, true, true);
				for (Integer i : selected_students_1) {
					studentLessThanX.remove(i);
					for (int row = 0; row < finalTotalMatrices.get(i).size(); row++) {
						finalTotalMatrices.get(i).set(row, 999.0);
						finalTotalMatrices.get(row).set(i, 999.0);

					}

				}
				selected_students.addAll(selected_students_1);
				teams.put(count, selected_students_1);
			}
			count++;

		}
		for (Map.Entry<Integer, ArrayList<Integer>> team : teams.entrySet()) {
			ArrayList<String> listTeam = new ArrayList<String>(teamSize);
			for (Integer id : team.getValue()) {
				listTeam.add(indexUserIndexToBannerID.get(id));
			}
			teamsWithBannerID.put(team.getKey(), listTeam);
		}
		System.out.println(Arrays.asList(teamsWithBannerID));
		return teamsWithBannerID;
	}

	private HashMap<String, HashMap<Integer, Integer>> GetAdditonalNumericMappings(
			List<ISurveyQuestionOptionsModel> numericTypeMatrix, Integer lessThanX, Integer greaterThanX,
			HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> studentWithQuestionAndAnswer,
			HashMap<String, Integer> indexUserBannerIdToIndex, IGroupFilter groupFilter) {

		HashMap<String, HashMap<Integer, Integer>> mappings = new HashMap<String, HashMap<Integer, Integer>>();
		HashMap<Integer, Integer> lessthanXValues = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> greaterthanXValues = new HashMap<Integer, Integer>();
		for (Map.Entry<String, Integer> user : indexUserBannerIdToIndex.entrySet()) {
			lessthanXValues.put(user.getValue(), 0);
			greaterthanXValues.put(user.getValue(), 0);
		}

		for (ISurveyQuestionOptionsModel question : numericTypeMatrix) {
			if (groupFilter.getConsiderLessThanOrGreaterThanX().containsKey(question.getSurveyQuestionId())) {
				lessThanX = groupFilter.getConsiderLessThanOrGreaterThanX().get(question.getSurveyQuestionId()).get(0);
				greaterThanX = groupFilter.getConsiderLessThanOrGreaterThanX().get(question.getSurveyQuestionId())
						.get(1);
			}
			for (Map.Entry<String, HashMap<Integer, ISurveyQuestionOptionsModel>> student : studentWithQuestionAndAnswer
					.entrySet()) {
				Integer studentKey = indexUserBannerIdToIndex.get(student.getKey());
				if (Integer.parseInt(
						student.getValue().get(question.getSurveyQuestionId()).getSurveyAnswers().get(0)) < lessThanX) {
					if (lessthanXValues.containsKey(studentKey)) {
						lessthanXValues.put(studentKey, lessthanXValues.get(studentKey) + 1);
					}
				}
				if (Integer.parseInt(student.getValue().get(question.getSurveyQuestionId()).getSurveyAnswers()
						.get(0)) > greaterThanX) {
					if (greaterthanXValues.containsKey(studentKey)) {
						greaterthanXValues.put(studentKey, greaterthanXValues.get(studentKey) + 1);
					}
				}
			}
		}
		for (Map.Entry<Integer, Integer> lessXvalue : lessthanXValues.entrySet()) {
			double avglessXvalue = lessXvalue.getValue() * 0.1 / (double) (numericTypeMatrix.size());
			if (avglessXvalue > 0.5) {
				lessXvalue.setValue(1);
			} else {
				lessXvalue.setValue(0);
			}
		}
		mappings.put("lessThanX", lessthanXValues);
		for (Map.Entry<Integer, Integer> greaterXvalue : greaterthanXValues.entrySet()) {
			double avggreatXvalue = greaterXvalue.getValue() * 0.1 / (double) (numericTypeMatrix.size());
			if (avggreatXvalue > 0.5) {
				greaterXvalue.setValue(1);
			} else {
				greaterXvalue.setValue(0);
			}
		}
		mappings.put("greaterThanX", greaterthanXValues);

		return mappings;
	}

	private ArrayList<ArrayList<Double>> AddMatrices(HashMap<Integer, ArrayList<ArrayList<Double>>> typeMatrices,
			boolean considerMean, int students) {
		ArrayList<ArrayList<Double>> totalMatrix = new ArrayList<ArrayList<Double>>(students);
		for (int i = 0; i < students; i++) {
			ArrayList<Double> col = new ArrayList<Double>(students);
			for (int j = 0; j < students; j++) {
				col.add(0.0);
			}
			totalMatrix.add(col);
		}
		for (Map.Entry<Integer, ArrayList<ArrayList<Double>>> questionMatrix : typeMatrices.entrySet()) {
			for (int row = 0; row < students; row++)
				for (int column = 0; column < students; column++)
					totalMatrix.get(row).set(column,
							totalMatrix.get(row).get(column) + questionMatrix.getValue().get(row).get(column));
		}
		if (considerMean) {
			for (int row = 0; row < students; row++)
				for (int column = 0; column < students; column++)
					totalMatrix.get(row).set(column, totalMatrix.get(row).get(column) / (double) typeMatrices.size());
		}
		for (int i = 0; i < totalMatrix.size(); i++) {
			for (int j = 0; j < totalMatrix.get(i).size(); j++) {
				System.out.print(totalMatrix.get(i).get(j));
				System.out.print(" ");
			}
			System.out.println();
		}
		return totalMatrix;
	}

	private HashMap<Integer, ArrayList<ArrayList<Double>>> formMultiChoiceMatrixForAllStudents(
			List<ISurveyQuestionOptionsModel> value, int students,
			HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> studentWithQuestionAndAnswer,
			Boolean similar, IGroupFilter groupFilter) {
		HashMap<Integer, ArrayList<ArrayList<Double>>> questionMatrix = new HashMap<Integer, ArrayList<ArrayList<Double>>>();
		for (ISurveyQuestionOptionsModel question : value) {
			ArrayList<ArrayList<Double>> matrix = new ArrayList<>(students);
			if (groupFilter.getquestionSimilarOrDissimilar().containsKey(question.getSurveyQuestionId())) {
				similar = groupFilter.getquestionSimilarOrDissimilar().get(question.getSurveyQuestionId());
			}
			for (Map.Entry<String, HashMap<Integer, ISurveyQuestionOptionsModel>> student : studentWithQuestionAndAnswer
					.entrySet()) {
				ArrayList<Double> row = new ArrayList<Double>(students);
				for (Map.Entry<String, HashMap<Integer, ISurveyQuestionOptionsModel>> secondStudent : studentWithQuestionAndAnswer
						.entrySet()) {
					if (student.getKey() == secondStudent.getKey()) {
						row.add(-999.9);
					} else {
						List<String> studentOptions = student.getValue().get(question.getSurveyQuestionId())
								.getSurveyAnswers();
						List<String> secondStudentOptions = secondStudent.getValue().get(question.getSurveyQuestionId())
								.getSurveyAnswers();
						List<String> intersect = studentOptions.stream().filter(secondStudentOptions::contains)
								.collect(Collectors.toList());
						double probabiltyValue = (double) (2 * intersect.size())
								/ (double) (studentOptions.size() + secondStudentOptions.size());

						if (similar) {
							row.add(probabiltyValue);
						} else {
							row.add(1 - probabiltyValue);
						}
					}
				}
				matrix.add(row);
			}
			questionMatrix.put(question.getSurveyQuestionId(), matrix);
		}
		return questionMatrix;

	}

	private HashMap<Integer, ArrayList<ArrayList<Double>>> formSingleChoiceOrNumericMatrixForAllStudents(
			List<ISurveyQuestionOptionsModel> value, int students,
			HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> studentWithQuestionAndAnswer,
			Boolean similar, IGroupFilter groupFilter) {
		HashMap<Integer, ArrayList<ArrayList<Double>>> questionMatrix = new HashMap<Integer, ArrayList<ArrayList<Double>>>();
		for (ISurveyQuestionOptionsModel question : value) {
			if (groupFilter.getquestionSimilarOrDissimilar().containsKey(question.getSurveyQuestionId())) {
				similar = groupFilter.getquestionSimilarOrDissimilar().get(question.getSurveyQuestionId());
			}
			ArrayList<ArrayList<Double>> matrix = new ArrayList<>(students);

			for (Map.Entry<String, HashMap<Integer, ISurveyQuestionOptionsModel>> student : studentWithQuestionAndAnswer
					.entrySet()) {
				ArrayList<Double> row = new ArrayList<Double>(students);
				for (Map.Entry<String, HashMap<Integer, ISurveyQuestionOptionsModel>> secondStudent : studentWithQuestionAndAnswer
						.entrySet()) {

					if (student.getKey() == secondStudent.getKey()) {
						row.add(-999.9);
					} else if (student.getValue().get(question.getSurveyQuestionId()).getSurveyAnswers().get(0).equals(
							secondStudent.getValue().get(question.getSurveyQuestionId()).getSurveyAnswers().get(0))) {
						if (similar) {
							row.add(0.0);
						} else {
							row.add(1.0);
						}
					} else {
						if (similar) {
							row.add(1.0);
						} else {
							row.add(0.0);
						}
					}
				}
				matrix.add(row);
			}
			questionMatrix.put(question.getSurveyQuestionId(), matrix);
		}
		return questionMatrix;

	}

	private HashMap<Integer, ArrayList<ArrayList<Double>>> formTextMatrixForAllStudents(
			List<ISurveyQuestionOptionsModel> value, int students,
			HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> studentWithQuestionAndAnswer, Boolean match,
			IGroupFilter groupFilter) {

		HashMap<Integer, ArrayList<ArrayList<Double>>> questionMatrix = new HashMap<Integer, ArrayList<ArrayList<Double>>>();
		for (ISurveyQuestionOptionsModel question : value) {
			if (groupFilter.getquestionSimilarOrDissimilar().containsKey(question.getSurveyQuestionId())) {
				match = groupFilter.getquestionSimilarOrDissimilar().get(question.getSurveyQuestionId());
			}
			ArrayList<ArrayList<Double>> matrix = new ArrayList<>(students);

			for (Map.Entry<String, HashMap<Integer, ISurveyQuestionOptionsModel>> student : studentWithQuestionAndAnswer
					.entrySet()) {
				ArrayList<Double> row = new ArrayList<Double>(students);
				for (Map.Entry<String, HashMap<Integer, ISurveyQuestionOptionsModel>> secondStudent : studentWithQuestionAndAnswer
						.entrySet()) {
					if (student.getKey() == secondStudent.getKey()) {
						row.add(-999.9);
					} else {
						LevenshteinDetailedDistance distance = new LevenshteinDetailedDistance();
						String studentText = student.getValue().get(question.getSurveyQuestionId()).getSurveyAnswers()
								.get(0);
						String secondStudentText = secondStudent.getValue().get(question.getSurveyQuestionId())
								.getSurveyAnswers().get(0);
						LevenshteinResults calcualtedDistance = distance.apply(studentText, secondStudentText);
						double probabiltyValue = calcualtedDistance.getDistance() * 100.0 / (double) 1000.0;

						if (match) {
							row.add(probabiltyValue);
						} else {
							row.add(1 - probabiltyValue);
						}
					}
				}
				matrix.add(row);
			}
			questionMatrix.put(question.getSurveyQuestionId(), matrix);
		}
		return questionMatrix;
	}

	private ArrayList<Integer> generate_team(ArrayList<Double> arrayList, int team_size,
			HashMap<Integer, Integer> studentLessThanX, HashMap<Integer, Integer> studentGreaterthanX,
			Boolean useLessthanX, Boolean useGreaterthanX) {

		System.out.println("new less dictionary");
		studentLessThanX.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " " + entry.getValue());
		});

		HashMap<Integer, Double> map = new HashMap<Integer, Double>();

//		Map<Double, Integer> map = new TreeMap<Double, Integer>();
		for (int i = 0; i < arrayList.size(); ++i) {

			map.put(i, arrayList.get(i));

		}
		System.out.println("arjun");
		HashMap<Integer, Double> sortedValues = sortByValue(map);
		Set<Integer> indices = sortedValues.keySet();

		ArrayList<Integer> top3 = new ArrayList<Integer>();
		Boolean notUsedLessthanX = true;
		Boolean notUsedgreaterthanX = true;

		for (Integer i : indices) {

			if (useGreaterthanX && notUsedgreaterthanX) {
				if (studentGreaterthanX.containsKey(i)) {
					if (studentGreaterthanX.get(i) == 0) {
						top3.add(i);
						System.out.print(i);
						System.out.print("-");
						notUsedgreaterthanX = false;
					}

				}

			}
			if (useLessthanX && notUsedLessthanX) {
				if (studentLessThanX.containsKey(i)) {
					if (studentLessThanX.get(i) == 1) {
						top3.add(i);
						System.out.print(i);
						System.out.print("-");
						notUsedLessthanX = false;
					}

				}

			}
			if (top3.size() == 2) {
				break;
			} else if (!(useLessthanX && useGreaterthanX)) {
				if (top3.size() == 1) {
					break;
				}
			}
		}
		for (Integer i : indices) {

			if (top3.size() == team_size) {
				break;
			} else if (!top3.contains(i) && sortedValues.get(i) != 999.0) {
				System.out.print(i);
				System.out.print("-");
				top3.add(i);
			}

		}

		System.out.println();
		return top3;
	}

	private HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
			public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
		for (Map.Entry<Integer, Double> aa : list) {
			System.out.println(aa.getKey() + "-" + aa.getValue());

			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	@Override
	public boolean saveGroupFormula(IGroupFilter groupFilter, String courseID) {

		return Injector.instance().getGrFormmerRepo().saveGroupFormula(groupFilter, courseID);
	}

	@Override
	public List<ISurveyQuestionOptionsModel> getSurveyQuestionsForGroupFormula(String courseId) {
		return Injector.instance().getAnswerSurveyRepository().getSurveyQuestionsForGroupFormula(courseId);
	}

	@Override
	public IGroupFilter createGroupFilterHashMap(HttpServletRequest req){
		IGroupFilter groupFilter = Injector.instance().getGroupFilter();

		HashMap<Integer, Boolean> groupFormResponses = new HashMap<>();
		HashMap<Integer, ArrayList<Integer>> considerLessThanOrGreaterThan = new HashMap<>();
		Enumeration e = req.getParameterNames();
		while(e.hasMoreElements()){
			String name = (String) e.nextElement();
			if(name.equals("_csrf")==false){
				String[] value = req.getParameterValues(name);
				ArrayList<Integer> tempList = new ArrayList<>();

				if(value[0].equals("Similar")){
					groupFormResponses.put(Integer.valueOf(name),true);
				}
				if(value[0].equals("Dissimilar")){
					groupFormResponses.put(Integer.valueOf(name),false);
				}

				if(value.length>1){
					for(int i=1;i<value.length;i++){
						tempList.add(Integer.valueOf(value[i]));
					}
					considerLessThanOrGreaterThan.put(Integer.valueOf(name),tempList);
				}
			}
		}
		groupFilter.setquestionSimilarOrDissimilar(groupFormResponses);
		groupFilter.setConsiderLessThanOrGreaterThanX(considerLessThanOrGreaterThan);
		return groupFilter;
	}
}
