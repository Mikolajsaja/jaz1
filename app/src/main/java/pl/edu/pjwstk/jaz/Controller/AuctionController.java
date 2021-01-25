package pl.edu.pjwstk.jaz.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.Request.Auction;
import pl.edu.pjwstk.jaz.Service.AuctionRepository;
import pl.edu.pjwstk.jaz.Service.ViewRepository;
import pl.edu.pjwstk.jaz.tables.ViewAuction;
import pl.edu.pjwstk.jaz.tables.ViewAuctionList;

import java.util.List;


@RestController
public class AuctionController {

    private final AuctionRepository auctionRepository;
    private final ViewRepository viewRepository;

    public AuctionController(AuctionRepository auctionRepository, ViewRepository viewRepository) {
        this.auctionRepository = auctionRepository;
        this.viewRepository = viewRepository;
    }

    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/addAuction")
    public void creteAuction(@RequestBody Auction auction){
        auctionRepository.createAuction(auction);
    }

    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/editAuction/{auction_id}")
    public void editAuction(@RequestBody Auction auction, @PathVariable Long auction_id){
        auctionRepository.editAuction(auction,auction_id);
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/auctions/{auctionId}")
    public ViewAuction getAuction(@PathVariable Long auctionId){
        return viewRepository.getAuction(auctionId);
    }

    @PreAuthorize("hasAnyAuthority('user')")
    @GetMapping("/auctions")
    public List<ViewAuctionList> getAuctions(){
        return viewRepository.getAuctionList();

    }


}

