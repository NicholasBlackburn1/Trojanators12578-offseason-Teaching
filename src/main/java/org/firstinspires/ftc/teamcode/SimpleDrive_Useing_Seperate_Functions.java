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

import java.io.File;


/*Sets Operational Mode to Controller opereated */
@TeleOp(name = "Simple Function Drive ", group = "TeleOp")
public class SimpleDrive_Useing_Seperate_Functions extends OpMode {

    // Define Hardware Vars
    private DcMotor BackR;
    private DcMotor BackL;
    private DcMotor FrontR;
    private DcMotor FrontL;
    private BNO055IMU imu;
    private DigitalChannel button;

    // Created vars for Orientation and Acceleration of imu
    public  Orientation angles;
    public Acceleration gravity;

    // Sets Sound File path
    private String soundPath = "/FIRST/blocks/sounds";
    private File screampath   = new File("/sdcard" + soundPath + "/core_death.wav");
    private File hellopath = new File("/sdcard" + soundPath + "/hello.wav");
    private File turretpath = new File("/sdcard" + soundPath + "/pew.wav");



    // Runs only on robot start up
    @Override
    public void init() {

        // enbles imu to be programend in code
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        // Defines Robot Drive motors in Java

        BackL = hardwareMap.dcMotor.get("BackL"); // Back set of wheels
        BackR = hardwareMap.dcMotor.get("BackR");

        FrontL = hardwareMap.dcMotor.get("FrontL"); // Front set of wheels
        FrontR = hardwareMap.dcMotor.get("FrontR");

        //adds digital input on channel 0 for button
         button = hardwareMap.get(DigitalChannel.class,"button");

        //Sets Drive motor Pos
        FrontR.setDirection(DcMotorSimple.Direction.FORWARD);
        BackL.setDirection(DcMotorSimple.Direction.REVERSE);

        FrontL.setDirection(DcMotorSimple.Direction.REVERSE);
        BackR.setDirection(DcMotorSimple.Direction.FORWARD);

        // Sets Zero Power Action for Drive Motor
        FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // Set to Brake mode
        FrontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BackR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Calabrates Imu
        ImuInit();

        // sets Digital mode to have an input
       button.setMode(DigitalChannel.Mode.INPUT);

        // When robot inits plays sound turret hello
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, hellopath);

    }

    @Override
    public void loop() {

        // Calls Function Drive
        Drive();

        // Calls Function Drive mode
        DriveMode();

        // Calls Function ImuData
        ImuData();

        turret();
        // Prints Data to the Driver station phone
        telemetry.addData("DriveMode",FrontR.getZeroPowerBehavior());

    }    // BackR Motor
    // custom function to drive motors
    public void Drive(){

        //Takes gampad1 leftstick y axis and right stick x axis to drive robot s

        FrontL.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x); // Front set of wheels
        FrontR.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);

        BackL.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x); // Back Set of Wheels s
        BackR.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);

    }
    public void DriveMode(){
        // Uses Button A on gampad to switch Motor Brake mode to Cost mode
        if (gamepad1.a == true){

            FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            FrontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            BackR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            BackL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        }
        // returns the Robot into Drive into Brake Mode
        if (gamepad1.a == false){
            FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FrontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            BackR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BackL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        }
    }

    public void ImuInit(){

        // Uses Expansion hub imu to see what axis robot is moving on
        BNO055IMU.Parameters imuParameters;
        imuParameters = new BNO055IMU.Parameters();
        // Use degrees as angle unit.
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        // Express acceleration as m/s^2.
        imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        // Disable logging.
        imuParameters.loggingEnabled = false;
        // Initialize IMU.
        imu.initialize(imuParameters);
        // Prompt user to press start buton.
        telemetry.addData("IMU Callabrated","is Callabrated");

    }
    public void ImuData(){
        // Get absolute orientation
        // Get acceleration due to force of gravity.
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        gravity = imu.getGravity();
        // Display orientation info.
        telemetry.addData("rot about Z", angles.firstAngle);
        telemetry.addData("rot about Y", angles.secondAngle);
        telemetry.addData("rot about X", angles.thirdAngle);
        // updates to screen
        telemetry.update();

        // if robot falls over than plays this file
        if(angles.secondAngle < -70.625){
            SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, screampath);
        }
        if(angles.secondAngle != -74.625){

        }
    }
    public void turret(){
        if (button.getState() ==true) {
            //SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, turretpath );
            telemetry.addData("Button","active");
            }

         if (button.getState() == false) {
             SoundPlayer.getInstance().startPlaying(hardwareMap.appContext,turretpath);
             }
         }
    }
