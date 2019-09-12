package org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import java.io.File;

/**
 * Maps out the Motors,Sensors Vars and FilePaths to be used in Robot OP_Modes
 */
public class RobotMap {

    public static DcMotor BackR;
    public static DcMotor BackL;
    public static DcMotor FrontR;
    public static DcMotor FrontL;

    public static BNO055IMU imu;
    public static DigitalChannel button;


    // Sets Sound File path
    public static String soundPath = "/FIRST/blocks/sounds";
    public static File screampath   = new File("/sdcard" + soundPath + "/core_death.wav");
    public static File hellopath = new File("/sdcard" + soundPath + "/hello.wav");
    public static File turretpath = new File("/sdcard" + soundPath + "/fizzel.wav");
    public static File disablepath = new File("/sdcard" + soundPath + "/disable.wav");




}
