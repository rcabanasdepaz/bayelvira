// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (d1_1 d1_2 d1_3);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d2_1 d2_2);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x1_1 x1_2 x1_3);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =3;
pos_y =5;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

// Links of the associated graph:

link D1 D2;

link D1 V0;

link D1 X2;

link D1 X3;

link D1 X4;

link D1 X6;

link D2 V0;

link D2 X2;

link X2 X1;

link X3 X2;

link X4 D2;

link X4 V0;

link X4 X6;

link X5 D2;

link X5 X4;

link X6 D2;

link X6 X1;

link X7 D1;

link X7 X4;

link X8 D1;

link X8 X7;

link X9 D1;

link X9 X7;

//Network Relationships: 

relation X7 X8 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7101443025423505 0.36905135914430115 0.5461949859394817 0.7242321935228132 0.28985569745764955 0.6309486408556988 0.45380501406051815 0.27576780647718685 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7456832889407211 0.25431671105927883 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.44391779256827496 0.5560822074317251 );
}

relation X4 D1 X5 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6107197652789212 0.8878552539679182 0.8607194138900336 0.5378273608362981 0.38021676144449046 0.8562470124651591 0.818028111485126 0.646811794614207 0.029815261800871865 0.5353660998388704 0.8186332994005602 0.027907323886205506 0.34208694420208097 0.49792928482576876 0.5877416500659836 0.48130564686905924 0.4324453381652116 0.7734375631939141 0.3892802347210788 0.11214474603208174 0.13928058610996652 0.462172639163702 0.6197832385555095 0.1437529875348409 0.181971888514874 0.353188205385793 0.9701847381991281 0.46463390016112954 0.1813667005994398 0.9720926761137945 0.657913055797919 0.5020707151742312 0.41225834993401644 0.5186943531309408 0.5675546618347883 0.226562436806086 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.42344392418313354 0.21048519135784166 0.3660708844590248 );
}

relation X6 D1 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.13485752472944113 0.6178322323226264 0.5230275947816895 0.4779677697491635 0.43777176868016127 0.45665493691803466 0.8651424752705589 0.38216776767737365 0.4769724052183106 0.5220322302508364 0.5622282313198387 0.5433450630819654 );
}

relation X1 X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5769873736747937 0.1858755738595148 0.2720792721212312 0.6913720869866367 0.33284723860229964 0.4126860572410872 0.09930453084567233 0.30649701037642635 0.09016538772290676 0.401438368899398 0.6286161970330967 0.002130902636936965 );
}

relation X2 D1 D2 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6516323383364102 0.1800638130500083 0.10969485336503239 0.12074030554209535 0.21655719356448763 0.2930507092383659 0.45339602103244164 0.7689884583518091 0.5514576690192496 0.05665107852359425 0.6750057805512409 0.9360880212253343 0.3483676616635899 0.8199361869499917 0.8903051466349676 0.8792596944579046 0.7834428064355123 0.706949290761634 0.5466039789675583 0.231011541648191 0.4485423309807503 0.9433489214764057 0.32499421944875906 0.06391197877466565 );
}

relation X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.49837374063272627 0.7975048378086624 0.645142927505831 0.5016262593672737 0.2024951621913375 0.3548570724941689 );
}

relation V0 D1 D2 X4 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (130.86902473067278 145.1722894093273 118.6409344785751 132.94419915722963 161.3465797935194 175.64984447217392 149.11848954142172 163.42175422007625 151.96965468226355 166.27291936091808 139.74156443016588 154.0448291088204 );
}

}
