 package org.firstinspires.ftc.teamcode;

import android.os.SystemClock;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
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


     double pusherPushing;
     double pusherClose;
     double pusherOpen;

     double doorClose;
     double doorOpen;

     double twisterDeliver;
     double twisterNeutral;




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
     int slideDown = robot.slide.getCurrentPosition();


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

         if (gamepad1.left_bumper && !robot.slideStop.isPressed()) {//goes
             setLevel(slideDown + 100);
         }

         ///////////////////////doors//////////////////////////////
         if(gamepad1.square){
             robot.pusher.setPosition(.7);////////retrieve state
         }
         if(gamepad1.triangle){
             robot.pusher.setPosition(1);///////pusher

             robot.twister.setPosition(.1);//////////forward

         }
         if(gamepad1.circle){
             robot.door.setPosition(.86);
             robot.twister.setPosition(.8);


         }

/*
         if (gamepad1.triangle) {
             if (!buttonPressed) {
                 retrieve_count += 1;
                 buttonPressed = true;
             } else {
                 buttonPressed = false;
             }

             if (retrieve_count % 2 == 0) {
                 robot.frontDoor.setPosition(86);
             } else {
                 robot.frontDoor.setPosition(0);
             }
         }

         if (gamepad1.square) {
             if (!buttonPressed) {
                 door_count += 1;
                 buttonPressed = true;
             } else {
                 buttonPressed = false;
             }

             if (door_count % 2 == 0) {
                 robot.door.setPosition(86);
             } else {
                 robot.door.setPosition(0);
             }
         }
*/

         ///////////////////////////intake//////////////////


                 // robot.topIntake.setPower(1);
                 //robot.windmill.setPower(1);

                 if (gamepad1.dpad_down && System.currentTimeMillis() - lastPressed > 500) {
                     lastPressed = System.currentTimeMillis();
                     motorOn = !motorOn;
                     if (robot.intakeLeft.getPower() == 0 && robot.intakeRight.getPower() == 0) {
                         robot.intakeLeft.setPower(1);
                         robot.intakeRight.setPower(1);
                         // robot.topIntake.setPower(1);
                         //robot.windmill.setPower(1);

                     } else {
                         robot.intakeRight.setPower(0);
                         robot.intakeLeft.setPower(0);
                         //robot.topIntake.setPower(0);
                         //  robot.windmill.setPower(0);


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
                 robot.shippingHubServo.setPosition(.86);
             } else {
                 robot.shippingHubServo.setPosition(0);
             }
         }
                 //////////////////turntable////////////////////
                 if (gamepad1.cross && System.currentTimeMillis() - lastPressed > 500) {
                     lastPressed = System.currentTimeMillis();
                     motorOn = !motorOn;
                     if (robot.turntableLeft.getPower() == 0 && robot.turntableRight.getPower() == 0 ) {
                         robot.turntableLeft.setPower(1);
                         robot.turntableRight.setPower(-1);

                     } else {
                         robot.turntableLeft.setPower(0);
                         robot.turntableRight.setPower(0);


                     }
                 }
//////////////////////////////////////////claw
         if(gamepad1.dpad_left){////////////close
             robot.claw.setPosition(0);
         }
         if(gamepad1.dpad_right){
             robot.claw.setPosition(86);
         }




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
     public void setLevel(int slideTarget){


         robot.slide.setTargetPosition(slideTarget);
         //move the slide

         robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         robot.slide.setPower(.5);

         while (robot.slide.isBusy()){
             telemetry.addData("SLIDE", "running to %7d : %7d",
                     slideTarget,
                     robot.slide.getCurrentPosition());
             //telemetry.addData(slideDataSTR);
             telemetry.update();
         }
         robot.slide.setPower(0);
         }}


