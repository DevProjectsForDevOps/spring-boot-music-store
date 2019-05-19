import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { DiscListComponent } from './shop/disc-list/disc-list.component';
import { DiscService } from './service/disc/disc.service';
import { UserFormComponent } from './public/register-form/register-form.component';
import { UserService } from './service/user/user-service.service';
import { LoginComponent } from './public/login/login.component';
import { MainPageComponent } from './public/main-page/main-page.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { DiscFormComponent } from './shop/disc-form/disc-form.component';
import { CartComponent } from './shop/cart/cart.component';
import { CartService } from './service/cart/cart-service.service';
import { OrderListComponent } from './shop/order-list/order-list.component';
import { OrderService } from './service/order/order.service';
import { RequestInterceptor } from './security/auth-service/request-interceptor';
import { AuthService } from './security/auth-service/auth.service';
import { DiscPageComponent } from './shop/disc-page/disc-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule, MatProgressBarModule, MatTabsModule, MatSidenavModule } from '@angular/material';
import { AddressComponent } from './shop/address/address.component';
import { AddressService } from './service/address/address.service';

@NgModule({
  declarations: [
    AppComponent,
    DiscListComponent,
    UserFormComponent,
    LoginComponent,
    MainPageComponent,
    NavbarComponent,
    FooterComponent,
    DiscFormComponent,
    CartComponent,
    OrderListComponent,
    DiscPageComponent,
    AddressComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatProgressBarModule,
    MatTabsModule,
    MatSidenavModule
  ],
  
  providers: [UserService, DiscService, CartService, OrderService, AuthService, AddressService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }