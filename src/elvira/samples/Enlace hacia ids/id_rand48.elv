// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
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
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
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

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
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

link D1 X10;

link D1 X3;

link D1 X7;

link D1 X8;

link D2 V0;

link D2 X3;

link D2 X4;

link X1 X10;

link X10 D2;

link X10 X9;

link X11 D1;

link X11 X13;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 X14;

link X14 D1;

link X14 X10;

link X2 X13;

link X2 X3;

link X2 X4;

link X5 D2;

link X5 X7;

link X5 X9;

link X6 D2;

link X6 X11;

link X7 D2;

link X7 X10;

link X8 D2;

link X8 X1;

link X8 X5;

link X9 D2;

link X9 X3;

//Network Relationships: 

relation X11 X6 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7797794693456291 0.29684270084794134 0.7840005631521232 0.0 0.22022053065437086 0.7031572991520586 0.21599943684787679 1.0 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X13 X2 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.21814340823601666 0.14984288942191612 1.0 0.6820780227298465 0.7818565917639834 0.850157110578084 0.0 0.3179219772701535 );
}

relation X14 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.40445362360639386 1.0 0.5955463763936061 0.0 );
}

relation X5 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 0.0 0.0 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X7 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.23145860476119384 0.1131738387962388 0.5943959740359948 0.8008953275560747 0.7685413952388062 0.8868261612037611 0.4056040259640051 0.19910467244392527 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.24662174446159713 0.0 0.7533782555384029 0.0 );
}

relation X9 X5 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.6440408774366736 0.722459789250774 1.0 0.0 0.35595912256332646 0.27754021074922597 0.0 );
}

relation X10 X1 X14 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.2039806212339414 0.03991492310112577 0.8398612057312613 0.7068998488003015 0.23666457025868967 0.0 0.0 0.7989509971303114 0.45781255642896895 0.19294213146717046 0.9714787798449386 0.9059900886515507 0.5160786037119236 0.46571699826920854 0.35301576100629983 0.0 0.7960193787660585 0.9600850768988742 0.16013879426873856 0.29310015119969846 0.7633354297413104 0.0 1.0 0.20104900286968866 0.542187443571031 0.8070578685328295 0.02852122015506138 0.09400991134844923 0.48392139628807634 0.5342830017307914 0.6469842389937002 );
}

relation X1 X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.20016643726139058 1.0 0.0 0.0 0.7998335627386094 0.0 1.0 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9931601875315333 0.006839812468466623 );
}

relation X3 D2 X2 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.7641705795022689 0.0 0.6624417371345178 1.0 0.0 0.7274143986619471 0.0 0.0 0.8478878118707867 0.8779103994194848 0.008699523519057496 0.10565929166905198 1.0 0.4737960298398192 0.313840922062635 1.0 0.23582942049773117 1.0 0.3375582628654822 0.0 1.0 0.2725856013380528 0.0 1.0 0.15211218812921326 0.12208960058051534 0.9913004764809425 0.894340708330948 0.0 0.5262039701601807 0.6861590779373651 );
}

relation X4 X2 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.11462789561548249 0.7103800878276285 0.0 0.0 0.8853721043845175 0.2896199121723715 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (96.7244485942044 136.72688502115528 56.99345257484948 96.99588900180035 );
}

}
