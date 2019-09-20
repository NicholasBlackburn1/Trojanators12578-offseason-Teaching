package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.ImuMapper;
import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.RobotMap;

@TeleOp(name = "Test  Drive ", group = "TeleOp")
public class test extends OpMode {

    ImuMapper map = new ImuMapper();
    @Override
    public void init() {
        map.Init();

        // Maping motor vars to the physical motor 

    }

    @Override
    public void loop() {

            map.ImuData();
    }
}