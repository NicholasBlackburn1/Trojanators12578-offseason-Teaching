package org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.RobotMap;
public class RobotOI extends OpMode {

    public void init() {
        RobotMap.imu = hardwareMap.get(BNO055IMU .class, "imu");

        // Defines Robot Drive motors in Java
        RobotMap.BackL = hardwareMap.dcMotor.get("BackL"); // Back set of wheels
        RobotMap.BackR = hardwareMap.dcMotor.get("BackR");

        RobotMap.FrontL = hardwareMap.dcMotor.get("FrontL"); // Front set of wheels
        RobotMap.FrontR = hardwareMap.dcMotor.get("FrontR");

    }

    @Override
    public void loop() {

    }
}
