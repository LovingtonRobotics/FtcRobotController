 package org.firstinspires.ftc.teamcode;

import android.os.SystemClock;

import com.qualcomm.robotcore.eventloop.opmode.FtcRobotControllerServiceState;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.ftccommon.FtcRobotControllerService;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.concurrent.TimeUnit;


 @TeleOp(name="Gamepad254", group="Linear Opmode")
 public class GamePad2 extends OpMode {
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

     int distance;


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
         telemetry.update();
         /////Drive System//////
         h = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
         robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
         rightX = gamepad1.right_stick_x;

         frontLeftPower = h * Math.sin(robotAngle) - rightX;
         frontRightPower = h * Math.cos(robotAngle) + rightX;
         backLeftPower = h * Math.cos(robotAngle) - rightX;
         backRightPower = h * Math.sin(robotAngle) + rightX;


         if (gamepad1.right_trigger > .5) {
             robot.frontRight.setPower(frontRightPower * pmodify);
             robot.frontLeft.setPower(frontLeftPower * pmodify);
             robot.backRight.setPower(backRightPower * pmodify);
             robot.backLeft.setPower(backLeftPower * pmodify);

         } else {
             robot.frontRight.setPower(frontRightPower);
             robot.frontLeft.setPower(frontLeftPower);
             robot.backRight.setPower(backRightPower);
             robot.backLeft.setPower(backLeftPower);
             // robot.cannon.setPower(cannonPower);
         }


//////////////////////////////////arm//////////////////////////////

         if (gamepad1.left_bumper) {//slide up
             robot.slide.setPower(0.5);


         } else if (gamepad1.left_trigger > .5) {// slide down
             robot.slide.setPower(-0.5);


         } else {
             robot.slide.setPower(0);
         }

         ///////////////////////doors//////////////////////////////
         if(gamepad1.square){
             robot.frontDoor.setPosition(20);////////door open to recieve, needs adjustment
         }
         if(gamepad1.triangle){
             robot.frontDoor.setPosition(0);///////////////door closed for when traveling up, needs adjustment
         }
         if(gamepad1.circle){
             robot.frontDoor.setPosition(0);
             robot.backDoor.setPosition(20);///////doors open and go backward to push freight onto hub, needs adjustment
         }


         ///////////////////////////intake//////////////////




                 if (gamepad1.dpad_down && System.currentTimeMillis() - lastPressed > 500) {
                     lastPressed = System.currentTimeMillis();
                     motorOn = !motorOn;
                     if (robot.intakeLeft.getPower() == 0 && robot.intakeRight.getPower() == 0) {
                         robot.intakeLeft.setPower(1);
                         robot.intakeRight.setPower(1);

                         /////////turn on both intakes

                     } else {
                         robot.intakeRight.setPower(0);
                         robot.intakeLeft.setPower(0);
                         //robot.topIntake.setPower(0);
                         //  robot.windmill.setPower(0);

                         //////turn off both intakes

                     }
                 }
//////////////////////////////////shippingHubServo
         if (gamepad1.right_bumper) {
             if (!buttonPressed) {
                 bumper_count += 1;
                 buttonPressed = true;
             } else {
                 buttonPressed = false;
             }

             if (bumper_count % 2 == 0) {
                 robot.shippingHubServo.setPosition(.86);///// goes up, needs adjustment
             } else {
                 robot.shippingHubServo.setPosition(0);/////goes down, needs adjustment
             }
         }


                 //////////////////turntable////////////////////
                 if (gamepad1.cross && System.currentTimeMillis() - lastPressed > 500) {
                     lastPressed = System.currentTimeMillis();
                     motorOn = !motorOn;
                     if (robot.turntableLeft.getPower() == 0 && robot.turntableRight.getPower() == 0 ) {
                         robot.turntableLeft.setPower(1);
                         robot.turntableRight.setPower(-1);
                         /////turns on both turntables

                     } else {
                         robot.turntableLeft.setPower(0);
                         robot.turntableRight.setPower(0);
                         ///////////turns off the turntables

                     }
                 }
//////////////////////////////////////////claw
         if(gamepad1.dpad_left){
             robot.claw.setPosition(0);/////////closes claw,needs adjustment
         }
         if(gamepad1.dpad_right){
             robot.claw.setPosition(86);/////////opens claw, needs adjustment
         }


         /////////////////////////////rumble testing

     /*   if(robot.colorDistance.getDistance(DistanceUnit.CM) < 10){
            gamepad1.rumble(.3,.3,2000);
        }*/
/////////////////Controlled Arm////////////
/*
                 if (gamepad1.dpad_left) {
                     armTarget = armTop;
                 }

                 if (gamepad1.dpad_left) {
                     armTarget = armTop;
                 }


                 if (gamepad1.dpad_right) {
                     armTarget = armIntake;
                 }
                 if (gamepad1.circle) {
                     robot.arm.setTargetPosition(armTarget);

                     robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                     while (robot.arm.isBusy()) {

                         telemetry.addData("ARM", "Running to %7d : %7d",
                                 armTarget,
                                 robot.arm.getCurrentPosition());
                         telemetry.update();


                         telemetry.addData("ARM", "Running to %7d : %7d",
                                 armTarget,
                                 robot.arm.getCurrentPosition());
                         telemetry.update();

                         // Stop all motion;
                         robot.backLeft.setPower(0);
                         robot.backRight.setPower(0);

                         // Turn off RUN_TO_POSITION
                         robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                         robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                         robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                         robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                     }


                         // Stop all motion;
                         robot.backLeft.setPower(0);
                         robot.backRight.setPower(0);

                         // Turn off RUN_TO_POSITION
                         robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                         robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                         robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                         robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                     }

                 }


                 }
*/
/////////////////// DriverControl Functions/////////////////////

             }
         }


