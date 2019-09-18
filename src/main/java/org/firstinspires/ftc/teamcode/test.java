package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class test extends OpMode {

    // Init motor vars
    public static DcMotor BackR;
    public static DcMotor BackL;
    public static DcMotor FrontR;
    public static DcMotor FrontL;

    @Override
    public void init() {
        // Maping motor vars to the physical motor 
        BackL = hardwareMap.dcMotor.get("BackL"); // Back set of wheels
        BackR = hardwareMap.dcMotor.get("BackR");

        FrontL = hardwareMap.dcMotor.get("FrontL"); // Front set of wheels
        FrontR = hardwareMap.dcMotor.get("FrontR");

        // Sets Drive motor Pos
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

        FrontL.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x); // Front set of wheels
        FrontR.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);

        BackL.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x); // Back Set of Wheels s
        BackR.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);
    }
}
