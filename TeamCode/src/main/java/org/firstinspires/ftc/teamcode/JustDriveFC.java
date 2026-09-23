package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
@TeleOp(name = "JustDriveFC")
public class JustDriveFC extends OpMode {
    //drive:)
    private Follower follower;

    @Override
    public void init() {
        //init drive:)
        follower = Constants.createFollower(hardwareMap);

    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {
        // drive controls:)
        follower.setTeleOpDrive(
               - gamepad1.left_stick_y,
               - gamepad1.left_stick_x,
                -gamepad1.right_stick_x,
                true
        );

        //final updates for follower:)
        follower.update();
    }
}
