package org.firstinspires.ftc.teamcode;

/*Team 12578 Robot Simple Chassie Drive for Teaching */

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.ImuMapper;
import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.OI;
import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.RobotMap;
import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.SoundMapper;


/*Sets Operational Mode to Controller opereated */
@TeleOp(name = "Simple Function Drive ", group = "TeleOp")
public class SimpleDrive_Useing_Seperate_Functions extends OpMode {

    // inits Drivetrain Functions and Imumapper Functions
        private static OI oi = new OI();
        private static DriveTrain Drive = new DriveTrain();
        private static ImuMapper ImuMap = new ImuMapper();

    // Runs only on robot start up
    @Override
    public void init() {

        oi.init();

        Drive.Init();

        ImuMap.Init();

        // When robot inits plays sound turret hello
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, SoundMapper.hellopath);

    }

    @Override
    public void loop() {
        // calls Reqular Motor Drive
        Drive.Motor_control();
        // Calls Imu to print out data
        ImuMap.ImuData();

        // Prints Data to the Driver station phone
        telemetry.addData("DriveMode", RobotMap.FrontR.getZeroPowerBehavior());
    }
         @Override
    public void stop(){
            telemetry.addData("Robot_Dead","true");
             SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, SoundMapper.disablepath);
        }
    }
