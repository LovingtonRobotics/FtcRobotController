package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="park", group="Test")
public class park extends automethods {
    HardwarePushbot robot = new HardwarePushbot();// Use a Pushbot's hardware


    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        //   robot.autoinit(hardwareMap);



        waitForStart();

////////////////////////////////////ROBOT  START////////////////////////////////////////////////////
        encoderDrive(0.7,60, 5);



    }}
