library(moments)
library(fitdistrplus)
library(tidyr)
library(actuar)
library(ggplot2)
library(evir)
library(ggExtra)
library(QRM)
library(MVN)

spl <- read.csv("C:/Users/Piotr Damrych/Desktop/UCZELNIA/SEM III/MM/spl.csv")
bnp <- read.csv("C:/Users/Piotr Damrych/Desktop/UCZELNIA/SEM III/MM/bnp.csv")

dane_otwarcia_spl <- spl$Otwarcie
dane_otwarcia_bnp <- bnp$Otwarcie

dane_zamkniecia_spl <- spl$Zamknięcie
dane_zamkniecia_bnp <- bnp$Zamknięcie

data_spl <- spl$Data
data_bnp <- bnp$Data

data_formatowna_spl <- as.Date(data_spl)
data_formatowna_bnp <- as.Date(data_bnp)

#1

hist(dane_zamkniecia_spl, main="Histogram kursów zamknięć SPL w latach 2020-2021", xlab="Kursy na zamknięciu", ylab="Częstotliwość")
hist(dane_zamkniecia_bnp, main="Histogram kursów zamknięć bnp w latach 2020-2021", xlab="Kursy na zamknięciu", ylab="Częstotliwość")

plot(data_formatowna_spl, dane_zamkniecia_spl, type="l", xlab="Lata", ylab="Kursy na zamknięciu", main = "Kursy zamknięcia SPL w latach 2020 - 2021")
plot(data_formatowna_bnp, dane_zamkniecia_bnp, type="l", xlab="Lata", ylab="Kursy na zamknięciu", main = "Kursy zamknięcia bnp w latach 2020 - 2021")

#2 
mean(dane_zamkniecia_spl)
mean(dane_zamkniecia_bnp)

sd(dane_zamkniecia_spl)
sd(dane_zamkniecia_bnp)


#install.packages("moments")
skewness(dane_zamkniecia_spl)
skewness(dane_zamkniecia_bnp)

kurtosis(dane_zamkniecia_spl)
kurtosis(dane_zamkniecia_bnp)

#3
#install.packages("fitdistrplus")
#SPL
fit_spl_norm <- fitdist(dane_zamkniecia_spl, "norm", method="mle")
plot(fit_spl_norm)

fit_spl_lnorm <- fitdist(dane_zamkniecia_spl, "lnorm", method="mle")
plot(fit_spl_lnorm)

#Gamma
fit_spl_gamma <- fitdist(dane_zamkniecia_spl, "gamma", method="mle")
plot(fit_spl_gamma)

#bnp
fit_bnp_norm <- fitdist(dane_zamkniecia_bnp, "norm", method="mle")
plot(fit_bnp_norm)

fit_bnp_lnorm <- fitdist(dane_zamkniecia_bnp, "lnorm", method="mle")
plot(fit_bnp_lnorm)

#Wykladniczy
fit_bnp_exp <- fitdist(dane_zamkniecia_bnp, "exp", method="mle")
plot(fit_bnp_exp)

#4 - w sumie 3 to to samo prawie
fn_spl <- fitdistrplus::fitdist(dane_zamkniecia_spl, "norm")
fl_spl <- fitdistrplus::fitdist(dane_zamkniecia_spl, "lnorm")
fg_spl <- fitdistrplus::fitdist(dane_zamkniecia_spl, "gamma")

fn_bnp <- fitdistrplus::fitdist(dane_zamkniecia_bnp, "norm")
fl_bnp <- fitdistrplus::fitdist(dane_zamkniecia_bnp, "lnorm")
fe_bnp <- fitdistrplus::fitdist(dane_zamkniecia_bnp, "exp")

plot.legend <- c("normal", "lnorm", "gamma")
denscomp(list(fn_spl, fl_spl, fg_spl), legendtext = plot.legend)
qqcomp(list(fn_spl, fl_spl, fg_spl), legendtext = plot.legend)
cdfcomp(list(fn_spl, fl_spl, fg_spl), legendtext = plot.legend)

plot.legend <- c("normal", "lnorm", "exp")
denscomp(list(fn_bnp, fl_bnp, fe_bnp), legendtext = plot.legend)
qqcomp(list(fn_bnp, fl_bnp, fe_bnp), legendtext = plot.legend)
cdfcomp(list(fn_bnp, fl_bnp, fe_bnp), legendtext = plot.legend)


gofstat(list(fn_spl, fl_spl, fg_spl),
        fitnames = c("norm", "lnorm", "gamma"))
#Kolomogorov-Smirnov - lnorm 

#Zadanie 5
N <- 10000
n <- length(dane_zamkniecia_spl); n

Dn <- c()
for (i in 1:N) {
  Yn <- rlnorm(n, fl_spl$estimate[1], fl_spl$estimate[2])
  Dn[i] <- ks.test(Yn, plnorm, fl_spl$estimate[1], fl_spl$estimate[2], exact = TRUE)$statistic
}

dn_n <- ks.test(dane_zamkniecia_spl, plnorm, fl_spl$estimate[[1]], fl_spl$estimate[[2]], exact = TRUE)$statistic
dn_n

par(mfrow=c(1,1))
hist(Dn, prob=T)
points(dn_n, 0, pch = 19, col = 2)

#wartosci p-value == 0 oznaczaja ze przy dowolnie przyjetym poziomie istotnsoci
#hipoteze o rownosci rozkladow odrzucamy

p_value <- length(Dn[Dn>dn_n])/N; p_value

#Przyjmujemy poziom istotnosci 5%
alpha <- 0.05
p_value <- alpha

# czesc 2

# log zwroty
s_bnp <- dane_zamkniecia_bnp
lns_bnp <- log(s_bnp)
diff_bnp <- diff(lns_bnp)

s_spl <- dane_zamkniecia_spl
lns_spl <- log(s_spl)
diff_spl <- diff(lns_spl)

df <- data.frame(bnp=diff_bnp, spl=diff_spl)
p <-  ggplot(df, aes(x=bnp, y=spl)) + geom_point()
ggMarginal(p, type="histogram")

# wektor średnich μ, macierz kowariancji i korelacji
mu <- colMeans(df)
Sigma <- cov(df)
P <- cor(df)
mu; Sigma; P;

s1 <- sd(diff_bnp)
s2 <- sd(diff_spl)

# kowariancja i wspolczynnik korelacji
kowariancja <- Sigma[2]
ws_korelacji <- Sigma[2]/(s1 * s2) 
kowariancja; ws_korelacji


step_x <- 6 * s1 / 100
step_y <- 6* s2  / 100

x     <- seq(-3*s1, 3*s1, step_x) 
y     <- seq(-3*s2, 3*s2, step_y)

f     <- function(x, y) dmnorm(cbind(x, y), mu, Sigma)  
z     <- outer(x, y, f)

par(mfrow=c(1,1))
persp(x, y, z, theta = -30, phi = 25, 
      shade = 0.75, col = "lightblue", expand = 0.5, r = 2, 
      ltheta = 25, ticktype = "detailed", xlab="Log zwroty BNP", ylab="Log zwroty SPL", zlab="Gestosc")

n <- nrow(df); n

set.seed(100)
Z <- MASS::mvrnorm(n,mu=mu,Sigma=Sigma)

par(mfrow=c(1,2))
plot(df, xlim=c(-0.15,0.15),ylim=c(-0.10,0.10))
plot(Z,xlim=c(-0.15,0.15),ylim=c(-0.10,0.10))

dM <- mahalanobis(df,mu,Sigma)

par(mfrow=c(1,1))
hist(dM,prob=TRUE)


n <- dim(df)[1]; n
alpha <- ppoints(n)
q_emp <- quantile(dM,alpha)
q_teo <- qchisq(alpha,df=2)

plot(q_emp,q_teo,pch=19)
abline(a=0,b=1,col=2)

ks.test(dM,'pchisq',2)

#p-value < 5% , zatem odrzucamy

MardiaTest(df)

par(mfrow=c(1,2))
result = mvn(data = df, mvnTest = "mardia",
             univariateTest = "AD", univariatePlot = "qq",
             multivariatePlot = "qq")

result

# czesc 3
z = qnorm(1-0.05/2, mean = 0, sd = 1) #kwantyl rzedu p rozkladu N(0,1)

#DANE
dane_spl <- diff_spl
# LICZEBNOSC PROBY
liczebnosc_spl <- length(diff_spl)
# SREDNIA PROBY
srednia_spl <- mean(diff_spl)
# SD Z PROBY
odchylenie_spl <- sd(diff_spl)

# dolna i gorna granica przedzialu ufnosci -- srednia +- (wartosci odczytane * odchylenie/pierwiastek(liczebnosci))
l_spl = srednia_spl - (z * odchylenie_spl/sqrt(liczebnosc_spl))
p_spl = srednia_spl + (z * odchylenie_spl/sqrt(liczebnosc_spl))
l_spl; p_spl;

# TERAZ TO SAMO Z BNP
dane_bnp <- diff_bnp
# LICZEBNOSC PROBY
liczebnosc_bnp <- length(diff_bnp)
# SREDNIA PROBY
srednia_bnp <- mean(diff_bnp)
# SD Z PROBY
odchylenie_bnp <- sd(diff_bnp)

# dolna i gorna granica przedzialu ufnosci -- srednia +- (wartosci odczytane * odchylenie/pierwiastek(liczebnosci))
l_bnp = srednia_bnp - (z * odchylenie_bnp/sqrt(liczebnosc_bnp))
p_bnp = srednia_bnp + (z * odchylenie_bnp/sqrt(liczebnosc_bnp))
l_bnp; p_bnp;


# REGRESJA
model <- lm(diff_spl ~ diff_bnp, data = df)
summary(model)
confint(model)
plot(model)

b0 <- model$coefficients[[1]]
b1 <- model$coefficients[[2]]

b0; b1

par(mfrow=c(1,1))
qplot(diff_spl, diff_bnp, data = df, 
      main = "Prosta regresji") + 
  geom_point(color = "blue", size=1) + 
  geom_abline(intercept = b0, slope = b1, color="red", size=1)


# RESZTY 
dane.lm <- lm(diff_spl~diff_bnp, data=df)
dane.lm

reszty <- dane.lm$residuals
reszty

hist(reszty)

qqnorm(reszty)
qqline(reszty,col=2)

m <- mean(reszty)
s <- sd(reszty)

ks.test(reszty,'pnorm',m,s)

#test Shapiro-Wilka
shapiro.test(reszty)

RSE <- sqrt(sum(reszty^2)/(length(diff_bnp)-2))
RSE

#p-value=2.67^10-11

# PREDYKCJE
m <- mean(diff_spl)

diff_spl.frame <- data.frame(diff_spl=m)

spolki.lm <- lm(diff_bnp~diff_spl,data=df)
predict(spolki.lm, diff_spl.frame, interval="confidence")
