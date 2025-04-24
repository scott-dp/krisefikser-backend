package stud.ntnu.no.krisefikser.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
  /**
   * Unique identifier for the user.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  /**
   * First name of the user.
   */
  @Column(nullable = false)
  private String firstName;

  /**
   * Last name of the user.
   */
  @Column(nullable = false)
  private String lastName;

  /**
   * Encrypted password of the user.
   */
  @Column(nullable = false)
  private String password;

  /**
   * Username of the user.
   * Must be unique and not null.
   */
  @Column(unique = true, nullable = false)
  private String email;
  
   /**
   * The household this user is associated with.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "household_id")
  private Household household;

  /**
   * Indicates whether the user is enabled.
   */
  @Column(name = "is_enabled", nullable = false)
  private boolean isEnabled = false;

  public String getUsername() {
    return email;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //TODO implement getauthorities correctly
    return List.of(new SimpleGrantedAuthority("USER"));
  }


  /**
   * Indicates whether the user's account has expired.
   * Always returns {@code true}, meaning accounts do not expire.
   *
   * @return {@code true} since the account never expires.
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user's account is locked.
   * Always returns {@code true}, meaning accounts are never locked.
   *
   * @return {@code true} since the account is never locked.
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Indicates whether the user's credentials (password) have expired.
   * Always returns {@code true}, meaning credentials never expire.
   *
   * @return {@code true} since credentials never expire.
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is enabled.
   *
   * @return true if the user is enabled, false otherwise.
   */
  @Override
  public boolean isEnabled() {
    return isEnabled;
  }
}
