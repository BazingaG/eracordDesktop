/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eracorddesktop;


public class pushAttendance extends Thread {

    public void run() {
        while (true) {
            jdbcConnection jdbccon = new jdbcConnection();
            jdbccon.get_attendances();
        }
    }

    public static void main(String[] args) {
        pushAttendance pa = new pushAttendance();
        pa.setName("pushattendanceThread");
        pa.start();
    }
}
