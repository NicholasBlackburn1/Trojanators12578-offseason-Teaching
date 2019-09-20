package org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class OI extends OpMode {

    public void init() {
        RobotMap.imu = hardwareMap.get(BNO055IMU.class,"imu");

        RobotMap.BackL = hardwareMap.dcMotor.get("BackL"); // Back set of wheels
        RobotMap.BackR = hardwareMap.dcMotor.get("BackR");

        RobotMap.FrontL = hardwareMap.dcMotor.get("FrontL"); // Front set of wheels
        RobotMap.FrontR = hardwareMap.dcMotor.get("FrontR");

    }


    public void loop() {

    }
}
