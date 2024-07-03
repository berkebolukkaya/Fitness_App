package com.project.cs310backendproject.controller;

import com.project.cs310backendproject.model.Exercise;
import com.project.cs310backendproject.model.ExercisePayload;
import com.project.cs310backendproject.model.User;
import com.project.cs310backendproject.model.WorkoutPayload;
import com.project.cs310backendproject.repo.ExerciseRepo;
import com.project.cs310backendproject.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class GymController {
    @Autowired private UserRepo userRepo;
    @Autowired private ExerciseRepo exerciseRepo;
    Logger logger = LoggerFactory.getLogger(GymController.class);


    //GET REQUESTS
    @GetMapping("/exercise")
    public List<Exercise> getExercise(){
        return exerciseRepo.findAll();
    }


    @GetMapping("/users")
    public List<String> getUserNames(){
        List<String> temp = new ArrayList<>();
        userRepo.findAll().forEach(s-> temp.add(s.getUsername()));
        return temp;
    }

    //POST REQUESTS
    @PostMapping("/register")
    public String registerUser(@RequestBody User user){

        if(!userRepo.findByUsername(user.getUsername()).isEmpty()){
            // Return a specific message
            return "User already exists";
        }
        userRepo.save(user);
        return "User registered successfully";
    }

    @PostMapping("/addToWorkout")
    public String addToWorkout(@RequestBody WorkoutPayload workout){
        User user = userRepo.findById(workout.getUserId()).get();
        Exercise exercise = exerciseRepo.findById(workout.getExerciseId()).get();

        if(user.getWorkoutPlan() == null){
            List<Exercise> workoutPlan = new ArrayList<>();
            workoutPlan.add(exercise);
            user.setWorkoutPlan(workoutPlan);
            userRepo.save(user);
            return exercise.getName() + " has been added to " + user.getUsername() + "'s plan!";
        }
        else{
            boolean add = true;
            for (Exercise s : user.getWorkoutPlan()) {
                if (s.getName().equals(exercise.getName())) {
                    add = false;
                    break;
                }
            }
            if (add) {
                user.getWorkoutPlan().add(exercise);
                userRepo.save(user);
                return exercise.getName() + " has been added to " + user.getUsername() + "'s plan!";
            }
            return exercise.getName() + " is already in " + user.getUsername() + "'s plan!";


        }

    }

    @PostMapping("/search")
    public Set<Exercise> searchExercise(@RequestBody ExercisePayload exercise){
        Set<Exercise> exercises = new HashSet<>();
        if(!exercise.getEquipment().isEmpty() && exercise.getMuscle().isEmpty()){
            for (String equipment : exercise.getEquipment()) {
                exercises.addAll(exerciseRepo.findByEquipmentContainsIgnoreCase(equipment));
            }
            return exercises;
        }
        else if(exercise.getEquipment().isEmpty() && !exercise.getMuscle().isEmpty()){
            for (String muscle : exercise.getMuscle()) {
                exercises.addAll(exerciseRepo.findByMuscleContainsIgnoreCase(muscle));
            }
            return exercises;
        }
        else if(!exercise.getEquipment().isEmpty() && !exercise.getMuscle().isEmpty()){
            for (String muscle : exercise.getMuscle()) {
                for (String equipment : exercise.getEquipment()) {
                    exercises.addAll(exerciseRepo.findByMuscleContainsIgnoreCaseAndEquipmentContainsIgnoreCase(muscle, equipment));
                }
            }
            return exercises;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Exercise Found");
        }
    }

    @PostMapping("/name")
    public List<Exercise> searchByName(@RequestBody ExercisePayload exercise){
        if(exercise.getName() != null){
            return exerciseRepo.findByNameContainsIgnoreCase(exercise.getName());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Exercise Found");

    }



        // EXERCISE DATABASE PART -------------------------------------------------------------------------------------------
    public void addExercise(String name, List<String> muscles, List<String> equipment, String description,List<Exercise> workout) {
        Exercise exercise = new Exercise(name, muscles, equipment, description);
        workout.add(exercise);
        exerciseRepo.save(exercise);
    }
    @PostConstruct
    public void init(){
        if(userRepo.count() == 0){
            List<Exercise> workout= new ArrayList<>();

            //---------------------------------------------
            //---------------------------------------------
            // Define equipment list-----------------------
            //---------------------------------------------
            //---------------------------------------------

            List<String> dumbbells = new ArrayList<>();
            dumbbells.add("Dumbbells");

            List<String> barbell = new ArrayList<>();
            barbell.add("Barbell");

            List<String> calfRaiseMachine = new ArrayList<>();
            calfRaiseMachine.add("CalfRaiseMachine");

            List<String> smithMachine = new ArrayList<>();
            smithMachine.add("SmithMachine");

            List<String> cableMachine = new ArrayList<>();
            cableMachine.add("CableMachine");

            List<String> abductionAdductionMachine = new ArrayList<>();
            abductionAdductionMachine.add("AbductionAdductionMachine");


            List<String> legCurlMachine = new ArrayList<>();
            legCurlMachine.add("LegCurlMachine");

            List<String> legPressMachine = new ArrayList<>();
            legPressMachine.add("LegPressMachine");

            List<String> legExtensionMachine = new ArrayList<>();
            legExtensionMachine.add("LegExtensionMachine");

            List<String> cableMachineBench = new ArrayList<>();
            cableMachineBench.add("CableMachine");
            cableMachineBench.add("Bench");

            List<String> tricepsPushdownCableMachine = new ArrayList<>();
            tricepsPushdownCableMachine.add("CableMachine");
            tricepsPushdownCableMachine.add("RopeAttachment");


            List<String> parallelBars = new ArrayList<>();
            parallelBars.add("ParallelBars");

            List<String> dumbbellsBench = new ArrayList<>();
            dumbbellsBench.add("Dumbbells");
            dumbbellsBench.add("Bench");

            List<String> barbellBench = new ArrayList<>();
            barbellBench.add("Barbell");
            barbellBench.add("Bench");

            List<String> zBar = new ArrayList<>();
            zBar.add("ZBar");

            List<String> pecDecMachine = new ArrayList<>();
            pecDecMachine.add("PecDecMachine");

            List<String> pullupBar = new ArrayList<>();
            pullupBar.add("PullupBar");

            List<String> backExtensionMachine = new ArrayList<>();
            backExtensionMachine.add("BackExtensionMachine");

            List<String> latPulldownCableMachine = new ArrayList<>();
            latPulldownCableMachine.add("LatPulldownCableMachine");


            List<String> inclineBenchDumbbells = new ArrayList<>();
            inclineBenchDumbbells.add("InclineBench");
            inclineBenchDumbbells.add("Dumbbells");

            //---------------------------------------------
            //---------------------------------------------
            // Define muscle lists ------------------------
            //---------------------------------------------
            //---------------------------------------------

            List<String> quadriceps = new ArrayList<>();
            quadriceps.add("Quadriceps");

            List<String> latissimusDorsi = new ArrayList<>();
            latissimusDorsi.add("LatissimusDorsi");

            List<String> frontDeltoids = new ArrayList<>();
            frontDeltoids.add("FrontDeltoids");

            List<String> sideDeltoids = new ArrayList<>();
            sideDeltoids.add("SideDeltoids");

            List<String> upperAbs = new ArrayList<>();
            upperAbs.add("UpperAbs");

            List<String> rearDeltoids = new ArrayList<>();
            rearDeltoids.add("RearDeltoids");

            List<String> upperBack = new ArrayList<>();
            upperBack.add("UpperBack");

            List<String> chest = new ArrayList<>();
            chest.add("Chest");

            List<String> obliques = new ArrayList<>();
            obliques.add("Obliques");

            List<String> lowerBack = new ArrayList<>();
            lowerBack.add("LowerBack");

            List<String> calves = new ArrayList<>();
            calves.add("Calves");
            
            List<String> lowerAbs = new ArrayList<>();
            lowerAbs.add("LowerAbs");

            List<String> biceps = new ArrayList<>();
            biceps.add("Biceps");

            List<String> hamstrings = new ArrayList<>();
            hamstrings.add("Hamstrings");

            List<String> glutes = new ArrayList<>();
            glutes.add("Glutes");

            List<String> triceps = new ArrayList<>();
            triceps.add("Triceps");

            List<String> bodyweight = new ArrayList<>();
            bodyweight.add("Bodyweight");

            List<String> forearms = new ArrayList<>();
            forearms.add("Forearms");


            //---------------------------------------------
            //---------------------------------------------
            // Adding exercises----------------------------
            //---------------------------------------------
            //---------------------------------------------


            addExercise(
                    "Seated Cable Fly",
                    chest,
                    cableMachineBench,
                    "Sit on a bench facing the cable machine. Grasp the handles with a slight bend in your elbows. Bring your hands together in front of you, focusing on squeezing your chest muscles. Return to the starting position, controlling the movement throughout.",
                    workout
            );

            addExercise(
                    "Incline Dumbbell Press (15 degree or 30)",
                    chest,
                    inclineBenchDumbbells,
                    "Lie back on a bench set at a 15 or 30-degree incline, holding a dumbbell in each hand at chest level. Press the dumbbells upward until your arms are fully extended, then lower them back down with control.",
                    workout
            );

            addExercise(
                    "Barbell Bench Press",
                    chest,
                    barbellBench,
                    "Lie back on a flat bench with a barbell racked above you. Grip the bar with hands slightly wider than shoulder-width apart. Lower the bar to your chest, then press it back up explosively to the starting position while maintaining control. This exercise primarily targets the chest muscles, as well as the shoulders and triceps.",
                    workout
            );

            addExercise(
                    "Slight Decline Dumbbell Press",
                    chest,
                    dumbbellsBench,
                    "Lie back on a bench set at a slight decline, holding a dumbbell in each hand at chest level. Press the dumbbells upward until your arms are fully extended, then lower them back down with control.",
                    workout
            );

            addExercise(
                    "Dips",
                    chest,
                    parallelBars,
                    "Position yourself on parallel bars with arms fully extended. Lower your body until your elbows are at a 90-degree angle, then push yourself back up to the starting position.",
                    workout
            );

            addExercise(
                    "Seated Dumbbell Shoulder Press",
                    frontDeltoids,
                    dumbbellsBench,
                    "Sit on a bench with back support, holding a dumbbell in each hand at shoulder height. Press the dumbbells upward until your arms are fully extended, then lower them back down with control.",
                    workout
            );

            addExercise(
                    "Dumbbell Lateral Raise",
                    sideDeltoids,
                    dumbbells,
                    "Stand with dumbbells at your sides. Raise your arms out to the sides until they're parallel to the ground, then lower them back down with control.",
                    workout
            );

            addExercise(
                    "Z bar Skullcrusher",
                    triceps,
                    zBar,
                    "Lie on a bench or a flat surface with a Z bar held straight up above your chest. Lower the bar by bending your elbows, keeping your upper arms stationary. Extend your arms back up, contracting your triceps.",
                    workout
            );

            addExercise(
                    "Dumbbell Skullcrusher",
                    triceps,
                    dumbbells,
                    "Lie on a bench or a flat surface with dumbbells held straight up above your chest. Lower the dumbbells by bending your elbows, keeping your upper arms stationary. Extend your arms back up, contracting your triceps.",
                    workout
            );

            addExercise(
                    "Triceps Pushdown",
                    triceps,
                    tricepsPushdownCableMachine,
                    "Stand in front of a cable machine with a rope attachment. Grasp the rope with palms facing down, keeping your elbows close to your body. Push the rope downward until your arms are fully extended, then return to the starting position.",
                    workout
            );

            addExercise(
                    "Push Ups",
                    chest,
                    bodyweight,
                    "Assume a prone position on the floor with hands placed wider than shoulder-width apart. Lower your body until your chest nearly touches the floor, then push yourself back up to the starting position.",
                    workout
            );

            addExercise(
                    "Pull Ups",
                    chest,
                    pullupBar,
                    "Hang from a pull-up bar with hands placed slightly wider than shoulder-width apart. Pull your body upward until your chin clears the bar, then lower yourself back down with control.",
                    workout
            );

            addExercise(
                    "Chin Ups",
                    chest,
                    pullupBar,
                    "Hang from a pull-up bar with hands placed closer than shoulder-width apart and palms facing towards you. Pull your body upward until your chin clears the bar, then lower yourself back down with control.",
                    workout
            );

            addExercise(
                    "Double Arm Reverse Cable Fly",
                    rearDeltoids,
                    cableMachine,
                    "Stand in front of a cable machine with handles attached at shoulder height. Grasp the handles with palms facing backwards. Pull the handles outwards, squeezing your shoulder blades together at the end of the movement.",
                    workout
            );
            addExercise(
                    "Back Extension",
                    lowerBack,
                    backExtensionMachine,
                    "Adjust the back extension machine so that your hips are aligned with the pad and your feet are secure. Cross your arms over your chest or place your hands behind your head. Lower your upper body towards the floor by bending at the waist, then raise it back up to the starting position.",
                    workout
            );

            addExercise(
                    "Lat Pulldown",
                    latissimusDorsi,
                    latPulldownCableMachine,
                    "Sit at the lat pulldown machine with your knees securely under the pads. Grasp the bar with an overhand grip wider than shoulder-width apart. Pull the bar down towards your chest, squeezing your shoulder blades together at the bottom of the movement, then slowly release it back up.",
                    workout
            );

            addExercise(
                    "Seated Cable Row",
                    latissimusDorsi,
                    cableMachine,
                    "Stand facing the cable machine with a rope attachment at chest height. Grasp the rope with palms facing each other. Pull the rope towards your body, keeping your elbows close to your sides and your shoulders back. Focus on squeezing your back muscles at the peak contraction, then slowly release the tension.",
                    workout
            );
            addExercise(
                    "Dumbbell Chest Supported Row",
                    latissimusDorsi,
                    dumbbellsBench,
                    "Lie face down on an incline bench with dumbbells in each hand, hanging straight down. Row the dumbbells upwards towards your armpits, squeezing your shoulder blades together at the top of the movement, then lower them back down with control.",
                    workout
            );

            addExercise(
                    "Spider Curl",
                    biceps,
                    dumbbellsBench,
                    "Sit at a preacher curl bench, placing your arms against the pad and holding a dumbbell in each hand with palms facing up. Curl the dumbbells upwards towards your shoulders, keeping your upper arms stationary, then lower them back down with control.",
                    workout
            );

            addExercise(
                    "Incline Dumbbell Curl",
                    biceps,
                    inclineBenchDumbbells,
                    "Sit on an incline bench with dumbbells in each hand, hanging straight down. Curl the dumbbells upwards towards your shoulders, keeping your upper arms stationary, then lower them back down with control.",
                    workout
            );

            addExercise(
                    "Hammer Curl",
                    biceps,
                    dumbbells,
                    "Stand with dumbbells at your sides, palms facing your body. Curl the dumbbells upwards towards your shoulders, keeping your palms facing each other throughout the movement, then lower them back down with control.",
                    workout
            );
            addExercise(
                    "Nyan Nyan",
                    forearms,
                    latPulldownCableMachine,
                    "Stand in front of a cable machine with a triceps rope attachment at chest height. Grasp the rope with palms facing down. Perform a wrist rotation motion, similar to the movement of an anime character saying 'Nyan Nyan', while keeping your forearms stationary.",
                    workout
            );
            addExercise(
                    "Pec Dec",
                    chest,
                    pecDecMachine,
                    "Sit at the pec dec machine with your back against the pad and grasp the handles. Bring the handles together in front of your chest, squeezing your pecs at the peak contraction, then slowly release back to the starting position.",
                    workout
            );
            addExercise(
                    "Reverse Grip Curl",
                    biceps,
                    barbell,
                    "Stand with a barbell in your hands, palms facing down and hands shoulder-width apart. Curl the barbell upwards towards your shoulders while keeping your elbows close to your body, then lower it back down with control.",
                    workout
            );

            addExercise(
                    "Reverse Cable Wrist Curl",
                    forearms,
                    cableMachine,
                    "Stand facing a cable machine with a straight bar attachment at waist height. Grasp the bar with an overhand grip, palms facing down. Curl your wrists upwards, bringing the bar towards your body, then lower it back down with control.",
                    workout
            );

            addExercise(
                    "Bulgarian Split Squats",
                    quadriceps,
                    dumbbells,
                    "Stand a few feet in front of a bench with a dumbbell in each hand. Rest one foot on the bench behind you. Lower your body into a lunge position, bending your front knee until it forms a 90-degree angle. Push through your front heel to return to the starting position.",
                    workout
            );

            addExercise(
                    "Hip Thrust",
                    glutes,
                    barbellBench,
                    "Sit on the ground with your upper back resting against a bench and a barbell across your hips. Plant your feet firmly on the ground in front of you. Thrust your hips upwards until your body forms a straight line from your shoulders to your knees, then lower back down with control.",
                    workout
            );
            addExercise(
                    "Leg Curl",
                    hamstrings,
                    legCurlMachine,
                    "Adjust the leg curl machine so that the padded lever rests on the back of your legs just above your heels. Curl your legs upwards towards your glutes, then lower them back down with control.",
                    workout
            );

            addExercise(
                    "Leg Press",
                    quadriceps,
                    legPressMachine,
                    "Sit on the leg press machine with your feet shoulder-width apart on the platform. Push the platform away from you by extending your knees until your legs are fully extended, then slowly lower the platform back down.",
                    workout
            );

            addExercise(
                    "Leg Extension",
                    quadriceps,
                    legExtensionMachine,
                    "Sit on the leg extension machine with your knees bent and the padded lever resting on your ankles. Extend your knees to lift the lever, then lower it back down with control.",
                    workout
            );

            addExercise(
                    "Romanian Deadlift",
                    hamstrings,
                    barbell,
                    "Stand with feet hip-width apart, holding a barbell in front of your thighs with an overhand grip. Keeping your back straight, hinge at the hips to lower the barbell towards the ground while keeping your legs relatively straight. Once you feel a stretch in your hamstrings, return to the starting position by driving your hips forward.",
                    workout
            );
            addExercise(
                    "Standing Smith Machine Calf Raise",
                    calves,
                    smithMachine,
                    "Stand with the balls of your feet on a platform under a smith machine bar. Unlock the bar and lift it off the safety catch. Rise up onto your toes as high as possible, then lower back down with control.",
                    workout
            );

            addExercise(
                    "Abduction-Adduction Machine",
                    glutes,
                    abductionAdductionMachine,
                    "Sit in the machine with your knees positioned against the pads. Adjust the machine so that your legs are either abducted (moving away from the body) or adducted (moving towards the body). Push against the pads to move your legs in the desired direction, then return to the starting position with control.",
                    workout
            );

            addExercise(
                    "Cable Crunch",
                    upperAbs,
                    cableMachine,
                    "Kneel facing away from the cable machine with a rope attachment overhead. Grasp the rope behind your head and crunch your torso downwards towards your knees, focusing on contracting your abdominal muscles. Return to the starting position with control.",
                    workout
            );
            addExercise(
                    "Crunch",
                    upperAbs,
                    bodyweight,
                    "Lie on your back with your knees bent and your feet flat on the floor. Place your hands behind your head or across your chest. Contract your abs to lift your shoulders off the floor, bringing your chest towards your knees. Hold for a moment, then lower back down with control.",
                    workout
            );

            addExercise(
                    "Hanging Leg Raise",
                    lowerAbs,
                    bodyweight,
                    "Hang from a pull-up bar with arms extended. Raise your legs by flexing your hips and knees until your thighs are at least parallel to the ground. Lower your legs back down with control.",
                    workout
            );
            addExercise(
                    "Smith Machine Squat",
                    quadriceps,
                    smithMachine,
                    "Stand with your feet shoulder-width apart under the bar of a smith machine. Position the bar across your upper back. Lower your body by bending your knees and hips, keeping your back straight, until your thighs are parallel to the ground. Push through your heels to return to the starting position.",
                    workout
            );

            addExercise(
                    "Military Press",
                    frontDeltoids,
                    barbell,
                    "Stand with your feet shoulder-width apart and hold a barbell at shoulder height with an overhand grip. Press the barbell overhead until your arms are fully extended, then lower it back down to shoulder height with control.",
                    workout
            );

            addExercise(
                    "Cable Face Pull",
                    rearDeltoids,
                    cableMachine,
                    "Attach a rope handle to the high pulley of a cable machine. Grasp the handles with palms facing each other and pull the handles towards your face, squeezing your shoulder blades together at the peak contraction. Return to the starting position with control.",
                    workout
            );

            addExercise(
                    "Machine Reverse Fly",
                    rearDeltoids,
                    pecDecMachine,
                    "Sit facing the machine with your chest against the pad and grasp the handles with your arms extended in front of you. Pull the handles out to the sides, squeezing your shoulder blades together at the peak contraction, then return to the starting position with control.",
                    workout
            );

            addExercise(
                    "Dumbbell Fly",
                    chest,
                    dumbbellsBench,
                    "Lie on a flat bench with a dumbbell in each hand, arms extended above your chest and palms facing each other. Lower the dumbbells out to the sides in a wide arc until you feel a stretch in your chest, then raise them back up to the starting position with control.",
                    workout
            );

            addExercise(
                    "Incline Rear Deltoid Fly",
                    rearDeltoids,
                    dumbbells,
                    "Lie face down on an incline bench with a dumbbell in each hand, arms hanging straight down. Raise your arms out to the sides until they are parallel to the ground, squeezing your rear deltoids at the top of the movement, then lower them back down with control.",
                    workout
            );

            addExercise(
                    "Bent Over Row",
                    upperBack,
                    barbell,
                    "Stand with your feet shoulder-width apart and hold a barbell with an overhand grip, hands slightly wider than shoulder-width apart. Hinge at the hips to bend forward until your torso is nearly parallel to the ground. Pull the barbell towards your lower chest, squeezing your shoulder blades together at the top of the movement, then lower it back down with control.",
                    workout
            );

            addExercise(
                    "Seated Calf Raise",
                    calves,
                    calfRaiseMachine,
                    "Sit on the calf raise machine with your knees bent and the balls of your feet on the platform. Raise your heels as high as possible by pushing through the balls of your feet, then lower them back down below the level of the platform with control.",
                    workout
            );
            addExercise(
                    "Russian Twists",
                    obliques,
                    bodyweight,
                    "Sit on the floor with your knees bent and your feet elevated off the ground. Hold a weight or medicine ball in front of you and twist your torso to one side, then to the other, while keeping your core engaged and your back straight.",
                    workout
            );
            addExercise(
                    "Bicycle",
                    obliques,
                    bodyweight,
                    "Lie on your back with your hands behind your head and your legs raised, knees bent at a 90-degree angle. Bring your right elbow towards your left knee while straightening your right leg, then switch sides, bringing your left elbow towards your right knee while straightening your left leg. Continue alternating sides in a pedaling motion while engaging your core muscles.",
                    workout
            );
            addExercise(
                    "Barbell Squat",
                    quadriceps,
                    barbell,
                    "Stand with your feet shoulder-width apart and a barbell across your upper back. Keeping your back straight and chest up, lower your body by bending your knees and hips, as if sitting back into a chair. Lower until your thighs are parallel to the ground, then push through your heels to return to the starting position.",
                    workout
            );


            User admin = new User(20,"mert","1234",workout);
            userRepo.save(admin);
            logger.info("Database is ready");
        }

    }

}
