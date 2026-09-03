package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.IntakeLogic;

import java.util.Timer;

@Autonomous
public class SampleAutonomous extends OpMode {
    private Follower follower;
    private Timer pathTimer, opModeTimer;

    private IntakeLogic intake = new IntakeLogic();

    @Override
    public void init() {
        intake.init(hardwareMap);
    }

    @Override
    public void loop() {
        follower.update();
        intake.update();
    }
}
