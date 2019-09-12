package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.io.File;
import java.util.Arrays;

@TeleOp(name = "Mecannu drive",group="Teleop")
public class Strafe_drive_puller extends OpMode {

    //Hardwar Vars
    private DcMotor BackR;
    private DcMotor BackL;
    private DcMotor FrontR;
    private DcMotor FrontL;
    private BNO055IMU imu;

    public Acceleration gravity;
    public Orientation angles;

    private String soundPath = "/FIRST/blocks/sounds";
    private File screampath   = new File("/sdcard" + soundPath + "/core_death.wav");
    private File hellopath = new File("/sdcard" + soundPath + "/hello.wav");

    @Override
    public void init() {
        // enbles imu to be programend in code
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        // Defines Robot Drive motors in Java

        BackL = hardwareMap.dcMotor.get("BackL"); // Back set of wheels
        BackR = hardwareMap.dcMotor.get("BackR");

        FrontL = hardwareMap.dcMotor.get("FrontL"); // Front set of wheels
        FrontR = hardwareMap.dcMotor.get("FrontR");

        FrontR.setDirection(DcMotorSimple.Direction.FORWARD);
        BackL.setDirection(DcMotorSimple.Direction.REVERSE);

        FrontL.setDirection(DcMotorSimple.Direction.REVERSE);
        BackR.setDirection(DcMotorSimple.Direction.FORWARD);

        // Sets Zero Power Action for Drive Motor
        FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // Set to Brake mode
        FrontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BackR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ImuInit();
    }

    @Override
    public void loop() {

        ImuData();
        Drive();
        mechnumm();
    }

    public void Drive(){
        //Takes gampad1 leftstick y axis and right stick x axis to drive robot s

        FrontL.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x); // Front set of wheels
        FrontR.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);

        BackL.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x); // Back Set of Wheels s
        BackR.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);

    }

    public void mechnumm(){
        if(gamepad1.a == true){
            double FrontLeftVal =  gamepad1.left_stick_y - (gamepad1.left_stick_x)  + -gamepad1.right_stick_x;
            double FrontRightVal =  gamepad1.left_stick_y  + (gamepad1.left_stick_x) - -gamepad1.right_stick_x;
            double BackLeftVal = gamepad1.left_stick_y  + (gamepad1.left_stick_x)  + -gamepad1.right_stick_x;
            double BackRightVal = gamepad1.left_stick_y - (gamepad1.left_stick_x) - -gamepad1.right_stick_x;

            //Move range to between 0 and +1, if not already
            double[] wheelPowers = {FrontRightVal, FrontLeftVal, BackLeftVal, BackRightVal};
            Arrays.sort(wheelPowers);
            if (wheelPowers[3] > 1) {
                FrontLeftVal /= wheelPowers[3];
                FrontRightVal /= wheelPowers[3];
                BackLeftVal /= wheelPowers[3];
                BackRightVal /= wheelPowers[3];
            }
            FrontL.setPower(FrontLeftVal);
            FrontR.setPower(FrontRightVal);
            BackL.setPower(BackLeftVal);
            BackR.setPower(BackRightVal);
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
        telemetry.addData("speed",imu.getAcceleration());
        // updates to screen
        telemetry.update();

        // if robot falls over than plays this file
        if(angles.secondAngle < -70.625){
            SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, screampath);
        }
        if(angles.secondAngle != -74.625){

        }

    }


}
