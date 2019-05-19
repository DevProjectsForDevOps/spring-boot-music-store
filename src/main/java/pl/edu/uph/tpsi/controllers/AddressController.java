package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.config.UserAuthentication;
import pl.edu.uph.tpsi.dto.AddressDTO;
import pl.edu.uph.tpsi.models.Address;
import pl.edu.uph.tpsi.services.AddressService;
import pl.edu.uph.tpsi.services.UserService;

@RestController
@RequestMapping ("/api/address")
public class AddressController
{
        private final AddressService addressService;

        private final UserService userService;

        private final UserAuthentication userAuthentication;

        @Autowired
        public AddressController ( AddressService addressService, UserService userService, UserAuthentication userAuthentication )
        {
                this.addressService = addressService;
                this.userService = userService;
                this.userAuthentication = userAuthentication;
        }

        @GetMapping
        public AddressDTO findOneByUser ( @RequestHeader ("Authorization") String auth )
        {
                return addressService.findOneByUser(
                        userService.getByUsername( userAuthentication.getUsername( auth ) )
                );
        }

        @PutMapping
        public AddressDTO update ( @RequestBody AddressDTO addressDTO,
                                   @RequestHeader ("Authorization") String auth )
        {
                return addressService.update(
                        userService.getByUsername( userAuthentication.getUsername( auth ) ).getAddress(),
                        addressDTO
                );
        }
}