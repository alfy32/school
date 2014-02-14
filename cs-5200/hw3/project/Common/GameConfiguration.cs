using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Common
{
    public class GameConfiguration : DistributableObject
    {
        #region Private Data Members and Properties
        // Define this class, identifier for serialization / deserialiation purposes
        private static Int16 ClassId { get { return (Int16) DISTRIBUTABLE_CLASS_IDS.GameConfiguration; } }

        #endregion

        #region Public Properties
        public Int16 PlayingFieldWidth { get; set; }
        public Int16 PlayingFieldHeight { get; set; }

        public Int16 BrilliantStudentRegistrationMin { get; set; }
        public Int16 BrilliantStudentRegistrationMax { get; set; }
        public Int16 ExcuseGeneratorRegistrationMin { get; set; }
        public Int16 ExcuseGeneratorRegistrationMax { get; set; }
        public Int16 WhiningSpinnerRegistrationMin { get; set; }
        public Int16 WhiningSpinnerRegistrationMax { get; set; }

        public float BrilliantStudentInitialStrength { get; set; }          // Strength units
        public float BrilliantStudentBaseSpeed { get; set; }                // Distance units (Squares) per move request (tick)
        public float BrilliantStudentSidewalkSpeedMultiplier { get; set; }  // Distance units (Squares) per move request (tick)
        public float BrilliantStudentDeathToZombieDelay { get; set; }       // Number of seconds before a dead student becomes a zombie

        public float ExcuseGeneratorInitialStrength { get; set; }           // Strength units
        public float ExcuseCreationRate { get; set; }                       // Excuses per tick or the inverse of ticks per excuse
        public float ExcuseCreationAcceleration { get; set; }               // Increase in creation rate per 10 seconds

        public float WhiningSpinnerInitialStrength { get; set; }            // Strength units
        public float WhiningTwineCreationRate { get; set; }                 // Excuses per tick or the inverse of ticks per excuse
        public float WhiningTwineCreationAcceleration { get; set; }         // Increase in creation rate per 10 seconds

        public Int16 ZombieInitialStrengthMin { get; set; }
        public Int16 ZombieInitialStrengthMax { get; set; }
        public float ZombieInitialSpeedMax { get; set; }
        public float ZombieInitialSpeedMin { get; set; }
        public float ZombieSidewalkSpeedMultiplier { get; set; }            // Distance units (Squares) per move request (tick)
        public float ZombieCreationRate { get; set; }                       // # per 10 seconds
        public float ZombieCreationAcceleration { get; set; }               // increase in creation rate per 10 seconds
        public float ZombieEatingRate { get; set; }                         // Strength units per ear request (tick)
        public float ZombieStrengthIncreaseForEatingStudent { get; set; }
        public float ZombieStrengthIncreaseForExcuseGenerator { get; set; }
        public float ZombieStrengthIncreaseForWhiningSpinner { get; set; }

        public Int16 BombExcuseDamage { get; set; }
        public float BombTwinePerSquareOfDistance { get; set; }             // Number of twine pieces per unit of distance
        public float BombDamageDiffusionFactor { get; set; }                // Percentage of remaining damage that can be spread to surround squares

        public Int16 TickLifetime { get; set; }                             // Number of seconds that a tick remains active
        public float TicksToStrengthRatio { get; set; }

        public static int MinimumEncodingLength
        {
            get
            {
                return 4              // Object header
                       + 2 * 10       // Int16 properties
                       + 4 * 21;      // float properties
            }
        }
        #endregion

        #region Constructors
        public GameConfiguration() { ResetToDefaults(); }
        
        /// <summary>
        /// Factor method to create a FieldLocation from a byte list
        /// </summary>
        /// <param name="bytes">A byte list from which the distributable object will be decoded</param>
        /// <returns>A new object of this class</returns>
        new public static GameConfiguration Create(ByteList bytes)
        {
            GameConfiguration result = new GameConfiguration();
            result.Decode(bytes);
            return result;
        }

        #endregion

        #region Encoding and Decoding methods

        /// <summary>
        /// This method encodes an object of this class into a byte list
        /// </summary>
        /// <param name="bytes"></param>
        public override void Encode(ByteList bytes)
        {
            bytes.Add(ClassId);                             // Write out the class type

            Int16 lengthPos = bytes.CurrentWritePosition;   // Get the current write position, so we
                                                            // can write the length here later

            bytes.Add((Int16) 0);                           // Write out a place holder for the length

            bytes.AddObjects(
                            PlayingFieldWidth,
                            PlayingFieldHeight,

                            BrilliantStudentRegistrationMin,
                            BrilliantStudentRegistrationMax,
                            ExcuseGeneratorRegistrationMin,
                            ExcuseGeneratorRegistrationMax,
                            WhiningSpinnerRegistrationMin,
                            WhiningSpinnerRegistrationMax,

                            BrilliantStudentInitialStrength,
                            BrilliantStudentBaseSpeed,
                            BrilliantStudentSidewalkSpeedMultiplier,
                            BrilliantStudentDeathToZombieDelay,

                            ExcuseGeneratorInitialStrength,
                            ExcuseCreationRate,
                            ExcuseCreationAcceleration,

                            WhiningSpinnerInitialStrength,
                            WhiningTwineCreationRate,
                            WhiningTwineCreationAcceleration,

                            ZombieInitialStrengthMin,
                            ZombieInitialStrengthMax,
                            ZombieInitialSpeedMax,
                            ZombieInitialSpeedMin,
                            ZombieSidewalkSpeedMultiplier,
                            ZombieCreationRate,
                            ZombieCreationAcceleration,
                            ZombieEatingRate,
                            ZombieStrengthIncreaseForEatingStudent,
                            ZombieStrengthIncreaseForExcuseGenerator,
                            ZombieStrengthIncreaseForWhiningSpinner,

                            BombExcuseDamage,
                            BombTwinePerSquareOfDistance,
                            BombDamageDiffusionFactor,

                            TickLifetime,
                            TicksToStrengthRatio);

            Int16 length = Convert.ToInt16(bytes.CurrentWritePosition - lengthPos - 2);
            bytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        
        }

        /// <summary>
        /// This method decodes of this classes from a byte list.  It can onlt be called from within the class hierarchy.
        /// </summary>
        /// <param name="messageBytes"></param>
        protected override void Decode(ByteList bytes)
        {
            if (bytes == null || bytes.RemainingToRead < MinimumEncodingLength)
                throw new ApplicationException("Invalid byte array");
            else if (bytes.PeekInt16() != ClassId)
                throw new ApplicationException("Invalid class id");
            else
            {
                Int16 objType = bytes.GetInt16();
                Int16 objLength = bytes.GetInt16();

                bytes.SetNewReadLimit(objLength);

                PlayingFieldWidth = bytes.GetInt16();
                PlayingFieldHeight = bytes.GetInt16();

                BrilliantStudentRegistrationMin = bytes.GetInt16();
                BrilliantStudentRegistrationMax = bytes.GetInt16();
                ExcuseGeneratorRegistrationMin = bytes.GetInt16();
                ExcuseGeneratorRegistrationMax = bytes.GetInt16();
                WhiningSpinnerRegistrationMin = bytes.GetInt16();
                WhiningSpinnerRegistrationMax = bytes.GetInt16();

                BrilliantStudentInitialStrength = bytes.GetFloat();
                BrilliantStudentBaseSpeed = bytes.GetFloat();
                BrilliantStudentSidewalkSpeedMultiplier = bytes.GetFloat();
                BrilliantStudentDeathToZombieDelay = bytes.GetFloat();

                ExcuseGeneratorInitialStrength = bytes.GetFloat();
                ExcuseCreationRate = bytes.GetFloat();
                ExcuseCreationAcceleration = bytes.GetFloat();

                WhiningSpinnerInitialStrength = bytes.GetFloat();
                WhiningTwineCreationRate = bytes.GetFloat();
                WhiningTwineCreationAcceleration = bytes.GetFloat();

                ZombieInitialStrengthMin = bytes.GetInt16();
                ZombieInitialStrengthMax = bytes.GetInt16();
                ZombieInitialSpeedMax = bytes.GetFloat();
                ZombieInitialSpeedMin = bytes.GetFloat();
                ZombieSidewalkSpeedMultiplier  = bytes.GetFloat();
                ZombieCreationRate  = bytes.GetFloat();
                ZombieCreationAcceleration  = bytes.GetFloat();
                ZombieEatingRate = bytes.GetFloat();
                ZombieStrengthIncreaseForEatingStudent = bytes.GetFloat();
                ZombieStrengthIncreaseForExcuseGenerator = bytes.GetFloat();
                ZombieStrengthIncreaseForWhiningSpinner = bytes.GetFloat();

                BombExcuseDamage = bytes.GetInt16();
                BombTwinePerSquareOfDistance = bytes.GetFloat();
                BombDamageDiffusionFactor = bytes.GetFloat();

                TickLifetime = bytes.GetInt16();
                TicksToStrengthRatio = bytes.GetFloat();

                bytes.RestorePreviosReadLimit();
            }
        }

        public void ResetToDefaults()
        {
            PlayingFieldWidth = 100;
            PlayingFieldHeight = 100;

            BrilliantStudentRegistrationMin = 10;
            BrilliantStudentRegistrationMax = 20;
            ExcuseGeneratorRegistrationMin = 10;
            ExcuseGeneratorRegistrationMax = 25;
            WhiningSpinnerRegistrationMin = 10;
            WhiningSpinnerRegistrationMax = 25;

            BrilliantStudentInitialStrength = 100.0F;
            BrilliantStudentBaseSpeed = .25F;
            BrilliantStudentSidewalkSpeedMultiplier = 1.5F;
            BrilliantStudentDeathToZombieDelay = 2.0F;

            ExcuseGeneratorInitialStrength = 100.0F;
            ExcuseCreationRate = 0.25F;
            ExcuseCreationAcceleration = 0.125F;

            WhiningSpinnerInitialStrength = 100.0F;
            WhiningTwineCreationRate = 0.25F;
            WhiningTwineCreationAcceleration = 0.125F;

            ZombieInitialStrengthMin = 25;
            ZombieInitialStrengthMax = 75;
            ZombieInitialSpeedMax = 0.15F;
            ZombieInitialSpeedMin = 0.5F;
            ZombieSidewalkSpeedMultiplier = 1.5F;
            ZombieCreationRate = 5.0F;
            ZombieCreationAcceleration = 0.5F;
            ZombieEatingRate = 2.0F;
            ZombieStrengthIncreaseForEatingStudent = 10.0F;
            ZombieStrengthIncreaseForExcuseGenerator = 5.0F;
            ZombieStrengthIncreaseForWhiningSpinner = 5.0F;

            BombExcuseDamage = 2;
            BombTwinePerSquareOfDistance = 2.0F;
            BombDamageDiffusionFactor = 0.75F;

            TickLifetime = 120;
            TicksToStrengthRatio = 1.0F;

        }

        #endregion

    }
}
