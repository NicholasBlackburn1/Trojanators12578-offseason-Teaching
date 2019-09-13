package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.ImuMapper;


@TeleOp(name = "Mecannu drive",group="Teleop")
public class Strafe_drive_puller extends OpMode {

    // Inits DriveTrain class As Drive

    public DriveTrain Drive = new DriveTrain();
    public ImuMapper ImuMap = new ImuMapper();

    @Override
    public void init() {

        // Init's Drive Train Init fun
        Drive.Init();

        ImuMap.Init();
        // inits Imu

    }

    @Override
    public void loop() {
        // calls Main Motor Drive
        Drive.Motor_control();

        ImuMap.ImuData();

        Drive.Strafe_active();

    }


}
