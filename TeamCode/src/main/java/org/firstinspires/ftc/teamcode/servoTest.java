package org.firstinspires.ftc.teamcode;

import android.os.SystemClock;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.concurrent.TimeUnit;


@TeleOp(name="servoTest4", group="Linear Opmode")
public class servoTest extends OpMode {
    HardwarePushbot robot = new HardwarePushbot();

    double robotAngle;
    double rightX;
    double h;

    double frontLeftPower;
    double backLeftPower;
    double frontRightPower;
    double backRightPower;

    double power;


    int slideTarget;
    int slideIntake;

    int armTop;
    int bumper_count;
    int door_count;
    int deliver_count;


    int acount;
    boolean aPressed;

    boolean buttonPressed;

    long lastPressed = 0;
    boolean motorOn = false;
    double pmodify = .25;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }


    @Override
    public void loop() {

        ///////////////////////doors//////////////////////////////
        if(gamepad1.square){

            robot.twister.setPosition(0.4);
        }


        if(gamepad1.circle){

            robot.twister.setPosition(.815);
        }












    }
}
