// Influence Diagram
//   Elvira format 

idiagram  "" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node Utility(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =50;
pos_y =1150;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

node EntryStrategy(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =1050;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 4;
states = ("Niche" "Differentiation" "PriceCutting" "AbandonEntry");
}

node Retaliation(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =497;
pos_y =51;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 2;
states = ("HighlyExpected" "NotExpected");
}

node LegalBarriers(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =119;
pos_y =124;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 3;
states = ("GovRegulation" "QuasiMonopoly" "Few");
}

node AccessToDistribution(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =50;
pos_y =750;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 3;
states = ("FreeAccess" "FixedCostsInCarryingNewProducts" "DistributorsPreferenceToEstablishedFirms");
}

node CustomerLoyalty(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =50;
pos_y =250;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 2;
states = ("Strong" "Weak");
}

node BrandAwareness(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =50;
pos_y =350;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 2;
states = ("Strong" "Weak");
}

node BarriersToEntry(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =458;
pos_y =220;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 2;
states = ("StrongBarriers" "WeakBarriers");
}

node ThreatOfEntry(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =950;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 2;
states = ("HightThreat" "LowThreat");
}

node ProductDifferentiation(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =50;
pos_y =850;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 3;
states = ("VeryHigh" "Moderate" "Low");
}

node AbsoluteCostAdvantageOfEntrant(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =50;
pos_y =650;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 2;
states = ("Yes" "No");
}

node EconomicsOfScale(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =50;
pos_y =550;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 2;
states = ("Necessary" "Unecessary");
}

node CapitalRequirements(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =560;
pos_y =429;
relevance = 7.0;
purpose = "";
HR_Grp="0";
num-states = 2;
states = ("Low" "High");
}

// Links of the associated graph:

link AbsoluteCostAdvantageOfEntrant AccessToDistribution;

link AbsoluteCostAdvantageOfEntrant ThreatOfEntry;

link AccessToDistribution ProductDifferentiation;

link AccessToDistribution ThreatOfEntry;

link BarriersToEntry EntryStrategy;

link BarriersToEntry Utility;

link BrandAwareness BarriersToEntry;

link BrandAwareness CapitalRequirements;

link CapitalRequirements EconomicsOfScale;

link CapitalRequirements ThreatOfEntry;

link CustomerLoyalty BarriersToEntry;

link CustomerLoyalty BrandAwareness;

link EconomicsOfScale AbsoluteCostAdvantageOfEntrant;

link EntryStrategy Utility;

link LegalBarriers BarriersToEntry;

link LegalBarriers CustomerLoyalty;

link ProductDifferentiation EntryStrategy;

link ProductDifferentiation ThreatOfEntry;

link Retaliation BarriersToEntry;

link Retaliation LegalBarriers;

link ThreatOfEntry EntryStrategy;

link ThreatOfEntry Utility;

//Network Relationships: 

relation Utility ThreatOfEntry BarriersToEntry EntryStrategy { 
comment = "";
kind-of-relation = utility;
deterministic=false;
values= table (8.0 4.0 1.0 9.0 2.0 -9.0 4.0 2.0 2.0 1.0 5.0 -8.0 -4.0 8.0 3.0 4.0 );
}

relation EntryStrategy ProductDifferentiation BarriersToEntry ThreatOfEntry { 
comment = "";
deterministic=false;
values= table (0.83 0.75 0.3 0.6 0.07 0.22 0.01 0.09174310917431092 0.01 0.0098039156862771 0.01 0.01 0.15 0.01 0.6 0.3 0.59 0.24 0.49 0.5045870504587051 0.07 0.0098039156862771 0.03 0.04 0.01 0.01 0.05 0.05 0.3 0.1 0.3 0.08256880825688083 0.8 0.04901957843138549 0.95 0.6 0.01 0.23 0.05 0.05 0.04 0.44 0.2 0.32110103211010327 0.12 0.9313725901960603 0.01 0.35 );
}

relation BarriersToEntry LegalBarriers Retaliation CustomerLoyalty BrandAwareness { 
comment = "";
deterministic=false;
values= table (0.99 0.97 0.95 0.9 0.85 0.8 0.75 0.7 0.65 0.6 0.55 0.53 0.47 0.45 0.4 0.35 0.3 0.25 0.2 0.15 0.1 0.05 0.03 0.01 0.01 0.03 0.05 0.1 0.15 0.2 0.25 0.3 0.35 0.4 0.45 0.47 0.53 0.55 0.6 0.65 0.7 0.75 0.8 0.85 0.9 0.95 0.97 0.99 );
}

relation ThreatOfEntry AccessToDistribution ProductDifferentiation AbsoluteCostAdvantageOfEntrant CapitalRequirements { 
comment = "";
deterministic=false;
values= table (0.98 0.97 0.94 0.91 0.88 0.851485 0.821782 0.8 0.77 0.74 0.71 0.68 0.65 0.62 0.59 0.56 0.53 0.51 0.49 0.47 0.44 0.41 0.475 0.35 0.32 0.29 0.26 0.23 0.2 0.17 0.14 0.11 0.08 0.05 0.02 0.01 0.02 0.03 0.06 0.09 0.12 0.148515 0.178218 0.2 0.23 0.26 0.29 0.32 0.35 0.38 0.41 0.44 0.47 0.49 0.51 0.53 0.56 0.59 0.525 0.65 0.68 0.71 0.74 0.77 0.8 0.83 0.86 0.89 0.92 0.95 0.98 0.99 );
}

}
