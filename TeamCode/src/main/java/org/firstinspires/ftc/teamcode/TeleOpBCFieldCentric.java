package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
@TeleOp(name = "TeleOpBCFieldCentric")
/*========================================================
Controls:
    GP 1:
    Joysticks to move/turn
    D-pad to turn/pitch turret

    GP 2:
    x --> intake
    y --> charge launch
    D-pad to turn/pitch turret
    Right trigger --> launch
 ========================================================*/
public class TeleOpBCFieldCentric extends OpMode {
    //drive:)
    private Follower follower;

    //other motors:)
    private DcMotor intake, launch0, launch1, turretRot;

    //servos:)
    private CRServo turretPitch, launch;

    //turret variables
    double turretPos = 0;
    boolean turretOn = false;


    @Override
    public void init() {
        //init drive:)
        follower = Constants.createFollower(hardwareMap);

        //init motors:)
        launch0 = hardwareMap.get(DcMotor.class, "launch0");
        launch1 = hardwareMap.get(DcMotor.class, "launch1");
        intake = hardwareMap.get(DcMotor.class, "intake");
        turretRot = hardwareMap.get(DcMotor.class,"turret");

        //init servo:)
        turretPitch = hardwareMap.get(CRServo.class,"turretPitch");
        launch = hardwareMap.get(CRServo.class,"launch");

    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {

        //turret variable updating:)
        turretPos = turretRot.getCurrentPosition();

        // drive controls:)
        follower.setTeleOpDrive(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x,
                false
        );

        //intake:)
        if(gamepad2.x){
            intake.setPower(1.0);
        } else{
            intake.setPower(0);
        }

        //launcher:)
        if(gamepad2.y && !turretOn){
            launch0.setPower(1.0);
            launch1.setPower(1.0);
        } else if(gamepad2.y && turretOn){
            launch0.setPower(0);
            launch1.setPower(0);
        }
        if(gamepad2.right_trigger_pressed){
            launch.setPower(0);
            //will actually add stuff when once mechanism decided
        }

        //turret pitch:)
        if(gamepad2.dpad_up || gamepad1.dpad_up){
            turretPitch.setPower(1.0);
        }else if(gamepad2.dpad_down || gamepad1.dpad_down){
            turretPitch.setPower(-1.0);
        }else{
            turretPitch.setPower(0);
        }

        //turret position w/ limiter:)
        if (gamepad1.dpad_left || gamepad2.dpad_left) {
            //CW limiting:)
            if (turretPos > -360) {
                turretRot.setPower(-1.0);
            } else {
                turretRot.setPower(0);
            }
        } else if (gamepad1.dpad_right || gamepad2.dpad_right) {
            // CCW limiting:)
            if (turretPos < 360) {
                turretRot.setPower(1.0);
            } else {
                turretRot.setPower(0);
            }
        }else {
            turretRot.setPower(0);
        }

        //final updates:)
        follower.update();
    }
}
//:)