package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uph.tpsi.dto.DiscDTO;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.repositories.DiscRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service ("discService")
public class DiscServiceImpl implements DiscService
{
        private final DiscRepository discRepository;

        @Autowired
        public DiscServiceImpl ( DiscRepository discRepository )
        {
                this.discRepository = discRepository;
        }

        @Override
        public List<DiscDTO> findAll ()
        {
                return discRepository.findAll()
                        .stream()
                        .map( DiscDTO::new )
                        .collect( Collectors.toList() );
        }

        @Override
        public DiscDTO findById ( Long id )
        {
                if ( id != null && id < 0 )
                        return discRepository.findById( 0L )
                                .map( DiscDTO::new )
                                .orElse( null );
                return discRepository.findById( Objects.requireNonNull( id ) )
                        .map( DiscDTO::new )
                        .orElse( null );
        }

        @Override
        public Long create ( DiscDTO disc )
        {
                return discRepository.save(
                        Disc.builder()
                                .band( disc.getBand() )
                                .amount( disc.getAmount() )
                                .price( disc.getPrice() )
                                .releaseDate( disc.getReleaseDate() )
                                .title( disc.getTitle() )
                                .deleted( false )
                                .build()
                ).getID();
        }

        @Override
        public DiscDTO update ( Long id, DiscDTO disc )
        {
                Disc discToReplace = discRepository.findById( id ).orElse( null );
                if ( discToReplace != null )
                {
                        discToReplace.setAmount( disc.getAmount() );
                        discToReplace.setBand( disc.getBand() );
                        discToReplace.setDeleted( disc.getDeleted() );
                        discToReplace.setPrice( disc.getPrice() );
                        discToReplace.setReleaseDate( disc.getReleaseDate() );
                        discRepository.save( discToReplace );
                }
                return disc;
        }

        @Override
        public boolean delete ( Long id )
        {
                if ( !discRepository.existsById( id ) )
                        return false;
                discRepository.deleteById( id );
                return true;
        }
}
