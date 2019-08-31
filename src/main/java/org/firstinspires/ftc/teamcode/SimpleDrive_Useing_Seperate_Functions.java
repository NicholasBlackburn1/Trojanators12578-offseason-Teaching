package org.firstinspires.ftc.teamcode;

/*Team 12578 Robot Simple Chassie Drive for Teaching */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/*Sets Operational Mode to Controller opereated */
@TeleOp(name = "Simple Function Drive ", group = "TeleOp")
public class SimpleDrive_Useing_Seperate_Functions extends OpMode {

    // Define Hardware Vars
    private DcMotor BackR;
    private DcMotor BackL;
    private DcMotor FrontR;
    private DcMotor FrontL;


        // Runs only on robot start up
    @Override
    public void init() {


        // Defines Robot Drive motors in Java
        BackL = hardwareMap.dcMotor.get("BackL"); // Back set of wheels
        BackR = hardwareMap.dcMotor.get("BackR");

        FrontL = hardwareMap.dcMotor.get("FrontL"); // Front set of wheels
        FrontR = hardwareMap.dcMotor.get("FrontR");

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

    }

    @Override
    public void loop() {

        // Calls Function Drive
        Drive();

        // Calls Function Drive mode
        DriveMode();

        // Prints Data to the Driver station phone
        telemetry.addData("DriveMode",FrontR.getZeroPowerBehavior());

    }
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

}
