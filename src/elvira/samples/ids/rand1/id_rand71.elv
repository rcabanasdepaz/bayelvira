// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d1_1 d1_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
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
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x1_1 x1_2);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
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

link D1 X1;

link D1 X5;

link D1 X6;

link D1 X7;

link D2 V0;

link D2 X3;

link D2 X4;

link X10 D1;

link X10 V0;

link X11 D1;

link X11 X10;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 V0;

link X13 X5;

link X13 X7;

link X13 X8;

link X2 X4;

link X3 X1;

link X5 D2;

link X5 V0;

link X6 D2;

link X6 X5;

link X7 D2;

link X7 V0;

link X8 D2;

link X8 X5;

link X9 D1;

link X9 X10;

//Network Relationships: 

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9879130725282795 0.012086927471720483 );
}

relation X10 X11 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8284353199582626 0.5814750455960547 0.3469734879242817 0.4720061616309171 0.17156468004173744 0.4185249544039453 0.6530265120757183 0.5279938383690829 );
}

relation X11 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5412241175454363 0.5303180533619395 0.4587758824545637 0.4696819466380604 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8325737843310262 0.16742621566897373 );
}

relation X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6058969393324147 0.3941030606675853 );
}

relation X5 D1 X13 X6 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.18001666971269617 0.5660049640560044 0.49182653750304484 0.5832004454965635 0.6938786009152995 0.3295115533616983 0.04132556481359513 0.934227108147028 0.4020022549240181 0.9186155791613005 0.24092010905198846 0.9048217396601973 0.5095762842895558 0.7076017441681904 0.37398158745681315 0.41445330063125085 0.8199833302873037 0.4339950359439956 0.508173462496955 0.41679955450343653 0.3061213990847005 0.6704884466383016 0.9586744351864049 0.065772891852972 0.5979977450759818 0.08138442083869941 0.7590798909480114 0.0951782603398026 0.4904237157104442 0.2923982558318096 0.6260184125431869 0.5855466993687491 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4301160221524774 0.3872745589181019 0.5698839778475226 0.612725441081898 );
}

relation X7 D1 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8891304973371297 0.3294540484661292 0.15708418395565163 0.2675964635307575 0.11086950266287035 0.6705459515338709 0.8429158160443484 0.7324035364692425 );
}

relation X8 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.17316807132414283 0.7540903497314753 0.8268319286758571 0.2459096502685247 );
}

relation X1 D1 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.48066208641068386 0.6946751313296327 0.9445358498581081 0.4206857113512972 0.5193379135893161 0.3053248686703674 0.055464150141891835 0.5793142886487029 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.45191981049916063 0.5480801895008393 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6274569533313413 0.3570260807591784 0.37254304666865873 0.6429739192408216 );
}

relation X4 D2 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7671109394123723 0.9517188573929147 0.22120246418336748 0.8002351800363665 0.23288906058762773 0.0482811426070852 0.7787975358166325 0.19976481996363352 );
}

relation V0 D1 D2 X10 X13 X5 X7 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (354.04267460268613 320.36656044940366 260.94671316128256 227.27059900800003 381.0833389284488 347.40722477516636 287.9873774870452 254.31126333376267 340.8349125438299 307.1587983905474 247.7389511024263 214.0628369491438 367.8755768695926 334.1994627163101 274.77961542818895 241.10350127490642 314.1071591282698 280.43104497498734 221.01119768686624 187.3350835335837 341.1478234540325 307.47170930075004 248.05186201262887 214.37574785934635 300.8993970694136 267.22328291613115 207.80343562801 174.1273214747275 327.94006139517626 294.2639472418938 234.84409995377263 201.16798580049013 384.5202296655328 350.8441155122503 291.42426822412915 257.7481540708467 411.5608939912954 377.88477983801295 318.4649325498918 284.7888183966093 371.31246760667653 337.63635345339407 278.2165061652729 244.5403920119904 398.35313193243917 364.6770177791567 305.25717049103554 271.58105633775307 344.5847141911165 310.90860003783405 251.4887527497129 217.81263859643036 371.6253785168791 337.9492643635966 278.5294170754755 244.853302922193 331.37695213226027 297.7008379789778 238.28099069085664 204.6048765375741 358.41761645802285 324.7415023047404 265.3216550166193 231.64554086333675 );
}

}
