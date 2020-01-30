import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradesOptimizer {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter number of subjects");
        int courses = scanner.nextInt();

        List<Course> allCourses = new ArrayList<>();
        for (int index = 0 ; index < courses ; index++) {
            String name = scanner.next();
            int credits = scanner.nextInt();
            int grade = scanner.nextInt();
            allCourses.add(new Course(name, credits, grade));
        }

//        allCourses.sort(Course::compareTo);
//        System.out.println(allCourses);

        Courses semester = new Courses(allCourses);
        System.out.println(semester.grade);
        System.out.println(semester.credits);

        System.out.println("Enter number of subjects that you wish to remove: ");
        int numberOfSubjectsDropped = scanner.nextInt();

        while (numberOfSubjectsDropped-- > 0) {
            semester.removeSubjectAndOptimize();
        }

        System.out.println(semester.grade);
        System.out.println(semester.credits);
    }

    private static class Courses {
        private final List<Course> allCourses;
        private int score;
        private double grade;
        private int credits;

        Courses(List<Course> allCourses) {
            this.allCourses = allCourses;
            for (Course course : allCourses) {
                score += course.credits * course.grade;
                credits += course.credits;
            }
            grade = (double) score / credits;
        }

        void removeSubjectAndOptimize() {
            int removalIndex = -1;
            for (int index = 0 ; index < allCourses.size() ; index++) {
                Course course = allCourses.get(index);
                double newGrade = (double) (score - course.credits*course.grade) / (credits - course.credits);
                if (newGrade > grade) {
                    grade = newGrade;
                    removalIndex = index;
                }
            }

            Course removedCourse = allCourses.remove(removalIndex);
            credits -= removedCourse.credits;
            score -= removedCourse.credits * removedCourse.grade;
            System.out.println(removedCourse.name);
        }

        public double getGrade() {
            return grade;
        }

        public int getCredits() {
            return credits;
        }
    }

    private static class Course implements Comparable<Course> {
        int grade;
        int credits;
        String name;

        Course(String name, int credits, int grade) {
            this.name = name;
            this.credits = credits;
            this.grade = grade;
        }

        @Override
        public int compareTo(Course course) {
            if (this.credits == course.credits && this.grade == course.grade) {
                return 0;
            }

            return ((double)this.grade/this.credits) > ((double) course.grade / course.credits) ? 1 : -1;
        }

        @Override
        public String toString() {
            return "{" +
                    "grade=" + grade +
                    ", credits=" + credits +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
