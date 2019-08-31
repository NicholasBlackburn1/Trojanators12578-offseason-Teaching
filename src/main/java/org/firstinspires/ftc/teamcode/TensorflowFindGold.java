package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_GOLD_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_SILVER_MINERAL;
@TeleOp (name = "FollowPerson", group = "TeleOp")
public class TensorflowFindGold extends OpMode {

    private static final String VUFORIA_KEY = "AfqrfAP/////AAABmbpuXhZBNUQwn1VZRlAAkjZyl/zIGqLIpcM+FSLaiHSsg7e2qHZDDAo2CFSDYXTxu/Zxp1hlDatILth7lcj9XX8murMllglToHb0078rANa/Vs4W1WKWObjC+tTeRi/icnNXPbv2APvIs0b8hYGV3fI3njZrXF/mm0u3uFYGZVFBQxWr6Ef/naDoxlrGFPNAR/7yxsnRBrWSlie6i9h19VjoXdu/Rf8gE72CwvXJYy9+DKbN12MlxdRmJx9U71p1SI4sZBY7yTTG0W/4UGEn4v4Nh86conA8o9aMe4/2wm4rGKJ8J75zr2+iubOf80cqvfU30D2dD3Vq95qy4EM+160iMKRKzS41UNZBFYrZayTN";
    public static  VuforiaLocalizer vuforia;
    public static  TFObjectDetector tfod;


    @Override
    public void init() {
        initVuforia();
        initTfod();
    }

    @Override
    public void loop() {
        if (tfod != null) {
            tfod.activate();


            }



    }

    // Init TensorFlow and Viewforia
    public  void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);


        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");

        }

        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters();
        tfodParameters.useObjectTracker = true;
        tfodParameters.minimumConfidence = 0.75;

    }
     // Inits Tensorflow
    public void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);

        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TfodRoverRuckus.TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);

        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
        if (updatedRecognitions != null) {
            telemetry.addData("# Object Detected", updatedRecognitions.size());


        }
        if (updatedRecognitions.size() == 3) {
            int goldMineralX = -1;
            int silverMineral1X = -1;
            int silverMineral2X = -1;
            for (Recognition recognition : updatedRecognitions) {
                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                    goldMineralX = (int) recognition.getLeft();
                } else if (silverMineral1X == -1) {
                    silverMineral1X = (int) recognition.getLeft();
                } else {
                    silverMineral2X = (int) recognition.getLeft();
                }
                if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                    if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                        telemetry.addData("Gold Mineral Position", "Left");
                    } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                        telemetry.addData("Gold Mineral Position", "Right");
                    } else {
                        telemetry.addData("Gold Mineral Position", "Center");
                    }
                }

            }

        }
    }
}
