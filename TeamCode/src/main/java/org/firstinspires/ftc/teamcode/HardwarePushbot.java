

package org.firstinspires.ftc.teamcode;


import android.app.Activity;
import android.view.View;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.TouchSensor;



public class HardwarePushbot {
    /* Public OpMode members. */
    ///////////////// Motors


    public static DcMotor frontLeft = null;
    public static DcMotor backLeft = null;
    public static DcMotor frontRight = null;
    public static DcMotor backRight = null;

    public static DcMotor slide = null;

    public static DcMotor shippingHub = null;

    public static DcMotor intakeRight = null;
    public static DcMotor intakeLeft = null;





    ///////////////Servos

//public static Servo testServo = null;
    /////////////Sensors
    //public static DistanceSensor FrontDistance = null;
    //public static DigitalChannel CascadeTouch = null;  // Hardware Device Object
    public static TouchSensor slideStop = null;

    public static CRServo turntableLeft = null;
    public static CRServo turntableRight = null;

    public static Servo pusher = null;
    public static Servo door = null;
    public static Servo claw = null;
    public static Servo twister = null;



    /*

        public static ColorSensor colorSensor;
        public static DistanceSensor colorDistance;
        public static DistanceSensor backRangeSensor;
        */
    public static BNO055IMU imu;// Additional Gyro device
    /* local OpMode members. */
    static HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public HardwarePushbot() {

    }

    /* Initialize standard Hardware interfaces */
    public static void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        backRight = hwMap.get(DcMotor.class, "backRight");

        slide = hwMap.get(DcMotor.class, "slide");

        intakeLeft = hwMap.get(DcMotor.class, "intakeLeft");
        intakeRight = hwMap.get(DcMotor.class, "intakeRight");

        shippingHub = hwMap.get(DcMotor.class, "shippingHub");







        // Define and initialize Servos
        turntableLeft =  hwMap.get(CRServo.class, "turntableLeft");
        turntableRight =  hwMap.get(CRServo.class, "turntableRight");

        door = hwMap.get(Servo.class, "door");
        pusher = hwMap.get(Servo.class, "pusher");
        claw = hwMap.get(Servo.class, "claw");
        twister = hwMap.get(Servo.class, "twister");


        //Define and Initialize Sensors///////////////////

        slideStop = hwMap.get(TouchSensor.class,"slideStop");




        imu = hwMap.get(BNO055IMU.class, "imu");

        // Set
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        slide.setDirection(DcMotor.Direction.FORWARD);

        intakeLeft.setDirection(DcMotor.Direction.FORWARD);
        intakeRight.setDirection(DcMotor.Direction.REVERSE);

        shippingHub.setDirection(DcMotorSimple.Direction.FORWARD);

        // Set all motors to zero power
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        slide.setPower(0);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);

        shippingHub.setPower(0);


        // rollers.setPower(0);


        // Set zero power behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        shippingHub.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //rollers.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shippingHub.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }
        public static void autoinit (HardwareMap ahwMap){
            // Save reference to Hardware map
            hwMap = ahwMap;
            int slideStart = slide.getCurrentPosition();

            //imu stuff
            imu = hwMap.get(BNO055IMU.class, "imu");
           BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
            parameters.loggingEnabled = true;
            parameters.loggingTag = "imu";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
            imu.initialize(parameters);


        //////close claw
            //
        }
    }