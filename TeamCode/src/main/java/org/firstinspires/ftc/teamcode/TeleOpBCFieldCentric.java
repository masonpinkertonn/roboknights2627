package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
@TeleOp(name = "TeleOpBCFieldCentric")
public class TeleOpBCFieldCentric extends OpMode {
    //drive:)
    private Follower follower;

    //other motors:)
    private DcMotor intake, launch0, launch1;

    //servos:)
    private CRServo turret;

    @Override
    public void init() {
        //init drive:)
        follower = Constants.createFollower(hardwareMap);

        //init motors:)
        launch0 = hardwareMap.get(DcMotor.class, "launch0");
        launch1 = hardwareMap.get(DcMotor.class, "launch1");
        intake = hardwareMap.get(DcMotor.class, "intake");

        //init sercos:)
        turret = hardwareMap.get(CRServo.class,"turret");

    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {
        // drive cntrols:)
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
        if(gamepad2.y){
            launch0.setPower(1.0);
            launch1.setPower(1.0);
        } else{
            launch0.setPower(0);
            launch1.setPower(0);
        }

        //turret:)

        //final updates for follower:)
        follower.update();
    }
}
