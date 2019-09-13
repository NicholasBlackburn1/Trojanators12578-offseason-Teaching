package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.DriveTrain;

import static org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.Imu.ImuData;
import static org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Drivetrain.Imu.ImuInit;

@TeleOp(name = "Mecannu drive",group="Teleop")
public class Strafe_drive_puller extends OpMode {

    // Inits DriveTrain class As Drive
    public DriveTrain Drive = new DriveTrain();

    @Override
    public void init() {

        // Init's Drive Train Init fun
        Drive.Init();

        // inits Imu
        ImuInit();
    }

    @Override
    public void loop() {
        // calls Main Motor Drive
        Drive.Motor_control();
        ImuData();
        mechnumm();
    }


    public void mechnumm(){
        if(gamepad1.a == true){

            Drive.Motor_Strafe_Control();

        }

    }

}
