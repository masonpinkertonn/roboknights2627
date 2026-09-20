package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp(name="TeleOpBiController", group="TeleOp")
public class TeleOpBiController extends LinearOpMode {

    //Pedro
    private Follower follower;
    // drive motors
    private DcMotor rf, rb, lb, lf;
    //other motors
    private DcMotor intake, launch0, launch1;
    //servos
    private CRServo turret;
    //turret timing
    double turretPos = 0;


    // Launch routine

    @Override
    public void runOpMode() {
        //motors

        rf = hardwareMap.get(DcMotor.class, "rf");
        rb = hardwareMap.get(DcMotor.class, "rb");
        lb = hardwareMap.get(DcMotor.class, "lb");
        lf = hardwareMap.get(DcMotor.class, "lf");

        intake = hardwareMap.get(DcMotor.class, "intake");
        launch0 = hardwareMap.get(DcMotor.class, "launch0");
        launch1 = hardwareMap.get(DcMotor.class, "launch1");

        turret = hardwareMap.get(CRServo.class, "turret");

        // Directions
        rf.setDirection(DcMotor.Direction.FORWARD);
        rb.setDirection(DcMotor.Direction.FORWARD);

        lf.setDirection(DcMotor.Direction.REVERSE);
        lb.setDirection(DcMotor.Direction.REVERSE);

        intake.setDirection(DcMotor.Direction.REVERSE);

        launch0.setDirection(DcMotorSimple.Direction.REVERSE);
        launch1.setDirection(DcMotorSimple.Direction.REVERSE);

        turret.setDirection(DcMotor.Direction.REVERSE);

        //turret code will come with limelight, for now it's manual w/ D-pad
        turret.setPower(0);

        telemetry.addLine("Pedro TeleOp Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            //driving

            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    gamepad1.right_stick_x
            );

            follower.update();

            // intake

            if (gamepad2.b) {

                intake.setPower(1);

            } else if (gamepad2.right_bumper) {

                intake.setPower(-1);

            } else if (gamepad2.x) {

                //how we would feed the launcher
                intake.setPower(1);

            } else {

                intake.setPower(0);
            }

            // turret
/*
            long buttonPressStart = 0;

            if (gamepad2.dpad_left) {

                if (buttonPressStart == 0) {
                    buttonPressStart = System.currentTimeMillis();
                    turret.setPower(0.5);
                }

                // calculates the turret position
                turretPos = -1 * 0.00006375 * System.currentTimeMillis() - buttonPressStart;
                //that long ahh number is rotations per ms in degrees defined by (60°speed*6)/(gear ratio*1000)

                //turret position limiters
                if (turretPos >= 360) {

                    while (turretPos > 0) {
                        turret.setPower(-1.0);
                        turretPos--;
                    }
                } else if (turretPos <= -360) {

                    while (turretPos < 0) {
                        turret.setPower(1.0);
                        turretPos--;
                    }
                }
            } else {

                turret.setPower(0);
            }
*/
            //launch

            if (gamepad2.y) {

                launch0.setPower(0.5);
                launch1.setPower(0.5);

            } else if (gamepad2.a) {

                launch0.setPower(1.0);
                launch1.setPower(1.0);

            } else if (gamepad2.dpad_up) {

                launch0.setPower(0.25);
                launch1.setPower(0.25);

            } else if (gamepad2.dpad_right) {

                launch0.setPower(0.5);
                launch1.setPower(0.5);

            } else if (gamepad2.dpad_down) {

                launch0.setPower(0.66);
                launch1.setPower(0.66);

            } else if (gamepad2.dpad_left) {

                launch0.setPower(0.83);
                launch1.setPower(0.83);

            } else {

                launch0.setPower(0);
                launch1.setPower(0);

            }

                //final updates
                follower.update();
                telemetry.update();

                idle();
            }
        }

    }