package org.firstinspires.ftc.teamcode;

/*Team 12578 Robot Simple Chassie Drive for Teaching */

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.DigitalSensors;
import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.RobotMap;

import java.io.File;

import static org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.Imu.ImuData;


/*Sets Operational Mode to Controller opereated */
@TeleOp(name = "Simple Function Drive ", group = "TeleOp")
public class SimpleDrive_Useing_Seperate_Functions extends OpMode {

    // Runs only on robot start up
    @Override
    public void init() {

        // enbles imu to be programend in code
        RobotMap.imu = hardwareMap.get(BNO055IMU.class, "imu");

        // Defines Robot Drive motors in Java
        RobotMap.BackL = hardwareMap.dcMotor.get("BackL"); // Back set of wheels
        RobotMap.BackR = hardwareMap.dcMotor.get("BackR");

        RobotMap.FrontL = hardwareMap.dcMotor.get("FrontL"); // Front set of wheels
        RobotMap.FrontR = hardwareMap.dcMotor.get("FrontR");

        //adds digital input on channel 0 for button
        RobotMap.button = hardwareMap.get(DigitalChannel.class,"button");

        DigitalSensors.Init();

        DriveTrain.Init();
        // When robot inits plays sound turret hello
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, RobotMap.hellopath);

    }

    @Override
    public void loop() {

        // Calls Function Drive
        Drive();

        // Calls Function Drive mode
        DriveMode();

        // Calls Function ImuData
        ImuData();

        // Prints Data to the Driver station phone
        telemetry.addData("DriveMode",RobotMap.FrontR.getZeroPowerBehavior());

    }    // BackR Motor
    // custom function to drive motors
    public void Drive(){


            RobotMap.FrontL.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x); // Front set of wheels
            RobotMap.FrontR.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);

            RobotMap.BackL.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x); // Back Set of Wheels s
            RobotMap.BackR.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);

    }
    public void DriveMode(){
        // Uses Button A on gampad to switch Motor Brake mode to Cost mode
        if (gamepad1.a == true){

            DriveTrain.Motor_Coast();
        }
        // returns the Robot into Drive into Brake Mode
        if (gamepad1.a == false){

            DriveTrain.Motor_Break();

        }
    }

         @Override
    public void stop(){
            telemetry.addData("Robot_Dead","true");
             SoundPlayer.getInstance().startPlaying(hardwareMap.appContext,RobotMap.disablepath);
        }
    }
