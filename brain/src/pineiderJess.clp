(import actions.*)
(import events.*)
;; Input events
(deftemplate BulletHit (declare (from-class BulletHit)))
(deftemplate BulletHitBullet (declare (from-class BulletHitBullet)))
(deftemplate BulletMissed (declare (from-class BulletMissed)))
(deftemplate Death (declare (from-class Death)))
(deftemplate HitByBullet (declare (from-class HitByBullet)))
(deftemplate HitRobot (declare (from-class HitRobot)))
(deftemplate HitWall (declare (from-class HitWall)))
(deftemplate RoundEnd (declare (from-class RoundEnd)))
(deftemplate RoundBegin (declare (from-class RoundBegin)))
(deftemplate ScannedRobot (declare (from-class ScannedRobot)))
(deftemplate Status (declare (from-class Status)))
(deftemplate Win (declare (from-class Win)))
;; Actions
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
;(defrule fire
;	
;	=>
;	(add (new Fire 1.0)))

;(defrule scan 
;	=>
;	(add (new Scan))) 

;(defrule init
;    ""
;    (RoundBegin)
;    =>
;    (assert (Scanning)))

(defrule scanning
    ""
    (not (ScannedRobot))
    =>
    (add (new TurnRadarRight 0.05)))


;(defrule updateStatus
;    ""
;    ?s <- (Status)
;    =>
;    )

(defrule narrowLock
    ""
    (ScannedRobot (bearing ?bearing))
    (Status (heading ?heading) (radarHeading ?radarHeading))
    =>
    (bind ?radarTurn (- (+ ?heading ?bearing) ?radarHeading))
    (add (new TurnRadarRight ?radarTurn)))

