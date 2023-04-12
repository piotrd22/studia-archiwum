import java.util.ArrayList;

public class GradeList {

    private final ArrayList<Double> grades;

    public GradeList() {
        grades = new ArrayList<>();
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public void removeGrades() {
        grades.clear();
    }

    public void addGrade(double grade) throws Exception {
        if (grade < 0) throw new Exception("Grade must be positive");

        grades.add(grade);
    }

    public double averageGrade() throws Exception {
        if (grades.isEmpty()) throw new Exception("There is no grade");

        double average = 0;
        for (Double grade : grades) {
            average += grade;
        }

        return average / grades.size();
    }

    public double highestGrade() throws Exception {
        if (grades.isEmpty()) throw new Exception("There is no grade");

        double max = grades.get(0);
        for (Double grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }

        return max;
    }

    public double lowestGrade() throws Exception {
        if (grades.isEmpty()) throw new Exception("There is no grade");

        double low = grades.get(0);
        for (Double grade : grades) {
            if (grade < low) {
                low = grade;
            }
        }

        return low;
    }
}
