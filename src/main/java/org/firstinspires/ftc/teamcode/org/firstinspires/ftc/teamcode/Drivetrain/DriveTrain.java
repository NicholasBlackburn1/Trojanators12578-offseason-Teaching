package org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;


public  class DriveTrain{

    public static void Init(){

        RobotMap.FrontR.setDirection(DcMotorSimple.Direction.FORWARD);
        RobotMap.BackL.setDirection(DcMotorSimple.Direction.REVERSE);

        RobotMap.FrontL.setDirection(DcMotorSimple.Direction.REVERSE);
        RobotMap.BackR.setDirection(DcMotorSimple.Direction.FORWARD);

        // Sets Zero Power Action for Drive Motor
        RobotMap.FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // Set to Brake mode
        RobotMap.FrontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RobotMap.BackR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RobotMap.BackL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RobotMap.BackL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotMap.BackR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Back Drive Motors

        RobotMap.FrontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);// Front Drive Motors
        RobotMap.FrontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public static void Motor_Coast() {
        RobotMap.FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RobotMap.FrontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        RobotMap.BackR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RobotMap.BackL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


    }

    public static void Motor_Break(){
        RobotMap.FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RobotMap.FrontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RobotMap.BackR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RobotMap.BackL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
