(import actions.*)
(import events.*)
;;Input events
(deftemplate BulletHit (declare (from-class BulletHit)))
(deftemplate BulletHitBullet (declare (from-class BulletHitBullet)))
(deftemplate BulletMissed (declare (from-class BulletMissed)))
(deftemplate Death (declare (from-class Death)))
(deftemplate HitByBullet (declare (from-class HitByBullet)))
(deftemplate HitRobot (declare (from-class HitRobot)))
(deftemplate HitWall (declare (from-class HitWall)))
(deftemplate RoundEnd (declare (from-class RoundEnd)))
(deftemplate ScannedRobot (declare (from-class ScannedRobot)))
(deftemplate Status (declare (from-class Status)))
(deftemplate Win (declare (from-class Win)))
;;Actions
(deftemplate Ahead (declare (from-class Ahead)))
(deftemplate Back (declare (from-class Back)))
(deftemplate Fire (declare (from-class Fire)))   
(deftemplate Scan (declare (from-class Scan))) 
(deftemplate TurnGunLeft (declare (from-class TurnGunLeft)))
(deftemplate TurnGunRight (declare (from-class TurnGunRight)))
(deftemplate TurnLeft (declare (from-class TurnLeft)))   
(deftemplate TurnRight (declare (from-class TurnRight)))
(deftemplate TurnRadarLeft (declare (from-class TurnRadarLeft)))  
(deftemplate TurnRadarRight (declare (from-class TurnRadarRight)))  
;; Define rules

;For Testing
(defrule fire
	(ScannedRobot)
	=>
	(add (new Fire 1.0)))

;While searching for enemy
(defrule scanForEnemy
	"search means moving randomly to avoid being scanned and scan everywhere"
    (Status (name ?name))
    (test(= ?name scanning))
	=> 
    (add (new TurnGunLeft 360))
    (assert (MoveRandom)))

(defrule enemyFound
	"stop scanning when the enemy has been seen"
    ?f <-(Status (name ?name&:(= ?name scanning)))
    (ScannedRobotEvent)
	=>
	(modify ?f (name tracking)))

;Tracking an enemy
(defrule pointAndShoot
    "if you see him point to him and shoot"
    (Status (name ?name&:(= ?name tracking)))
    (ScannedRobot (bearing ?bears) (distance ?dist))   
    =>
    (add (new Fire 1))
    (add (new TurnRight ?bears))
    (add (new Ahead ?dist))
    (add (new Scan))
    (assert (evasiveMove)))

(defrule wireAtWill
    "on previous hit, fire again (try a killing shot)"
    (BulletHit (energy ?energy))
    =>
    (add (new Fire (/ ?energy 5))))

(defrule zigZag
    "go ahead moving right and left"
    ?f <-(Ahead (distance ?dist&:(> ?dist 200)))
    ?m <-(evasiveMove)
    =>
    (retract ?f)
    (retract ?m)
    (add (new TurnLeft 45))
    (add (new Ahead 70))
    (add (new TurnRight 90))
    (add (new Ahead 70))
    (add (new TurnLeft 90))
    (add (new Ahead 70))
    (add (new TurnRight 45)))

;Common behaviours
(defrule distanceBasedFireEfficiency
	"modify fire value to save energy based on distance"
    (ScannedRobot (distance ?dist&:(> ?dist 10)))
    ?f <-(Fire (power ?pow&:(< ?pow 3)))
    (test (and (< ?pow 3) (> ?pow 0.5)))
	=>
    
    (add (new Fire (* ?pow (/ 100 ?dist))))
    (retract ?f))

(defrule previousHitfireEfficiency
	"modify fire value to save energy based on current Hit"
    (BulletHit (energy ?energy))
    ?f <-(Fire (power ?pow&:(< ?pow 3)))
	=>
    (modify ?f (power (* ?pow 2))))

(defrule randomMove
    "Perform a random Move"
    ?f <-(MoveRandom)
    =>
    (retract ?f)
    (bind ?steering (-(/ (random) 183) 180)) ;; steere between -180 +180
    (add(new TurnLeft ?steering))
    (bind ?movement (-(/ (random) 1024) 32)) ;; move forward between -32 and 32 pixels
    (add (new Ahead ?movement)))

(defrule turnGunRightOptimization
    ?f <- (TurnGunRight (degree ?value&:(> ?value 180)))
    =>
    (retract ?f)
    (add (new TurnGunLeft (- ?value 180))))

(defrule turnGunLeftOptimization
    ?f <- (TurnGunLeft (degree ?value&:(> ?value 180)))
    =>
    (retract ?f)
    (add (new TurnGunRight (- ?value 180))))

(defrule turnRightOptimization
    ?f <- (TurnRight (degree ?value&:(> ?value 180)))
    =>
    (retract ?f)
    (add (new TurnLeft (- ?value 180))))

(defrule turnLeftOptimization
    ?f <- (TurnLeft (degree ?value&:(> ?value 180)))
    =>
    (retract ?f)
    (add (new TurnRight (- ?value 180))))

(defrule turnRadarRightOptimization
    ?f <- (TurnRadarRight (degree ?value&:(> ?value 180)))
    =>
    (retract ?f)
    (add (new TurnRadarLeft (- ?value 180))))

(defrule turnRadarLeftOptimization
    ?f <- (TurnRadarLeft (degree ?value&:(> ?value 180)))
    =>
    (retract ?f)
    (add (new TurnRadarRight (- ?value 180))))

