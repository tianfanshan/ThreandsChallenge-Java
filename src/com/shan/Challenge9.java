package com.shan;

public class Challenge9 {
    public static void main(String[] args) {
        final NewTutor tutor = new NewTutor();
        final NewStudent student = new NewStudent(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });

        tutorThread.start();
        studentThread.start();
    }
}

class NewTutor {
    private NewStudent student;

    public void setStudent(NewStudent student) {
        this.student = student;
    }

    public void studyTime() {



//        synchronized (this) {
//            System.out.println("Tutor has arrived");
//            synchronized (student) {
//                try {
//                    // wait for student to arrive
//                    this.wait();
//                } catch (InterruptedException e) {
//
//                }
//                student.startStudy();
//                System.out.println("Tutor is studying with student");
//            }
//        }



        // =====================Solution==========================
        synchronized (this) {
            System.out.println("Tutor has arrived");
            try {
                System.out.println("Waiting");
                // wait for student to arrive
                this.wait();

            } catch (InterruptedException e) {

            }
            synchronized (student) {

                student.startStudy();
                System.out.println("Tutor is studying with student");
            }
        }
        // =====================Solution==========================
    }

    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

class NewStudent {

    private NewTutor tutor;

    NewStudent(NewTutor tutor) {
        this.tutor = tutor;
    }

    public void startStudy() {
        // study
        System.out.println("Student is studying");
    }

    public void handInAssignment() {
        synchronized (tutor) {
            tutor.getProgressReport();
            synchronized (this) {
                System.out.println("Student handed in assignment");
                tutor.notifyAll();
            }
        }
    }
}