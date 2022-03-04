package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name="autonomous", group="Test")
public class autonomous extends automethods {
    HardwarePushbot robot = new HardwarePushbot();// Use a Pushbot's hardware
    OpenCvWebcam webcam;
    testPipeline.BarcodeDeterminationPipeline pipeline;
    testPipeline.BarcodeDeterminationPipeline.barcodePosition barcodePos;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        robot.autoinit(hardwareMap);
        slideDown = robot.slide.getCurrentPosition();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);
        pipeline = new testPipeline.BarcodeDeterminationPipeline();
        webcam.setPipeline(pipeline);
        webcam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);


        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        while(!isStarted( ) && !isStopRequested()){
            telemetry.addData("Analysis",pipeline.getAnalysis());
            telemetry.update();

            sleep(50);
        }

////////////////////////////////////ROBOT  START////////////////////////////////////////////////////

/*
        encoderDrive(.5, 80, 6);
        strafeRight(.5,-90,6);
        setLevel(2);*/



////////////////////////////////

        if(barcodePos == testPipeline.BarcodeDeterminationPipeline.barcodePosition.LEFT){
            setLevel(1);
            wait(5);
            setLevelDown(slideDown);
        }
        else if(barcodePos == testPipeline.BarcodeDeterminationPipeline.barcodePosition.CENTER ) {
            setLevel(2);
            wait(5);
            setLevelDown(slideDown);
        }
        else if(barcodePos == testPipeline.BarcodeDeterminationPipeline.barcodePosition.RIGHT){
            setLevel(3);
            wait(5);
            setLevelDown(slideDown);
        }
    }
}
